<?php
require '../hidden/dbpassword.php';

// Opens and returns a DB connection
function openDBConnection () {
	global $dbpassword;
	$DBH = new PDO($dbHost, $dbUser, $dbpassword);
	$DBH->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $DBH;
}

function storeResume ($resumeName, $name, $phone, $addr, $position, $startdate, $enddate, $desc){
// 	echo $name.$phone.$addr.$position;
	
	try{
		//get id
		$id = getResumeID($resumeName);
		
		if ($id <= 0){
			createResume($resumeName);
			$id = getResumeID($resumeName);
		}

		storeContactInfo($id, $name, $phone, $addr);
		storePositionSought($id, $position);
		storeEmploymentHistory($id, $startdate, $enddate, $desc);
		
		return true;
	}
	catch (PDOException $e){
		if ($e->getCode() == 23000) {
			return false;
		}
		reportDBError($e);
	}
}

function createResume($resumeName){
	//create resume if not in database
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	$query2 = $DBH->prepare("insert into ResumeNames (ResumeName) values (?)");
	$query2->bindValue(1, $resumeName);
	$query2->execute();
	return $DBH->commit();
}

function getResumeID ($resumeName){
	//get the ResumeID
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	$stmt = $DBH->prepare("select * from ResumeNames where ResumeName=?");
	$stmt->bindValue(1, $resumeName);
	$stmt->execute();
	$rows = $stmt->fetchAll();
	$DBH->commit();
	
	//not found
	if (count($rows) == 0)
		return -1;
	
	return $rows[0]['ResumeID'];
}

function storeContactInfo ($id, $name, $phone, $addr){
	global $insert;
	$DBH = openDBConnection();
	$DBH->beginTransaction();

	//Choose either update or insert
	$stmt = $DBH->prepare("insert into ContactInfo (Name, Phone, Address, ID) values (?,?,?,?)
			on duplicate key update Name=?, Phone=?, Address=?");
		
	$stmt->bindValue(1, $name);
	$stmt->bindValue(2, $phone);
	$stmt->bindValue(3, $addr);
	$stmt->bindValue(4, $id);
	$stmt->bindValue(5, $name);
	$stmt->bindValue(6, $phone);
	$stmt->bindValue(7, $addr);
	$stmt->execute();
	return $DBH->commit();
}

function storePositionSought ($id, $position){
	global $insert;
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	//Choose either update or insert
	$stmt = $DBH->prepare("insert into PositionSought (JobDesc, ID) values (?,?)
			on duplicate key update JobDesc=?");

	$stmt->bindValue(1, $position);
	$stmt->bindValue(2, $id);
	$stmt->bindValue(3, $position);
	$stmt->execute();
	return $DBH->commit();
}

function storeEmploymentHistory ($id, $startdate, $enddate, $desc){
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$startdate = serialize($startdate);
	$enddate = serialize($enddate);
	$desc = serialize($desc);

	$stmt = $DBH->prepare("insert into EmploymentHistory (ID, StartDate, EndDate, Description) values (?,?,?,?)
			on duplicate key update StartDate=?, EndDate=?, Description=?");
	$stmt->bindValue(1, $id);
	$stmt->bindValue(2, $startdate);
	$stmt->bindValue(3, $enddate);
	$stmt->bindValue(4, $desc);
	$stmt->bindValue(5, $startdate);
	$stmt->bindValue(6, $enddate);
	$stmt->bindValue(7, $desc);
	$stmt->execute();
	
	return $DBH->commit();
}

function deleteResume ($resumeName){
	$id = getResumeID($resumeName);
	
	if ($id <= 0)
		return false;
	
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$stmt = $DBH->prepare("delete from ResumeNames where ResumeID=?");
	$stmt->bindValue(1, $id);
	$stmt->execute();
	
	return $DBH->commit();
}

function loadResume ($resumeName){
	$id = getResumeID($resumeName);
	
	if ($id <= 0)
		return false;
	
	loadContactInfo($id);
	loadPositionSought($id);
	loadEmploymentHistory($id);
	
	return true;
}

function viewResume ($resumeName){
	$id = getResumeID($resumeName);
	
	if ($id <= 0)
		return null;
	
	$contactInfo = viewContactInfo($id);
	$position = viewPositionSought($id);
	$empHistory = viewEmploymentHistory($id);
	
	$array = array(
			"contactInfo" => $contactInfo,
			"position" => $position,
			"empHistory" => $empHistory
			);
	
	return $array;
}

function loadContactInfo($id){
	$row = viewContactInfo($id);
	
	$_SESSION['name'] = $row['Name'];
	$_SESSION['phone'] = $row['Phone'];
	$_SESSION['address'] = $row['Address'];	
}

function viewContactInfo($id){
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$stmt = $DBH->prepare("select * from ContactInfo where ID=?");
	$stmt->bindValue(1, $id);
	$stmt->execute();
	
	$row = $stmt->fetch();
	$DBH->commit();
	return $row;
}

function loadPositionSought($id){
	$row = viewPositionSought($id);
	$_SESSION['position'] = $row['JobDesc'];
}

function viewPositionSought ($id){
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$stmt = $DBH->prepare("select * from PositionSought where ID=?");
	$stmt->bindValue(1, $id);
	$stmt->execute();
	
	$row = $stmt->fetch();
	$DBH->commit();
	return $row;
}

function loadEmploymentHistory($id){
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$q1 = $DBH->prepare("select StartDate from EmploymentHistory where ID=?");
	$q2 = $DBH->prepare("select EndDate from EmploymentHistory where ID=?");
	$q3 = $DBH->prepare("select Description from EmploymentHistory where ID=?");
	$q1->bindValue(1, $id);
	$q2->bindValue(1, $id);
	$q3->bindValue(1, $id);
	$q1->execute();
	$q2->execute();
	$q3->execute();
	
	$startdates = $q1->fetch(PDO::FETCH_COLUMN);
	$enddates = $q2->fetch(PDO::FETCH_COLUMN);
	$descs = $q3->fetch(PDO::FETCH_COLUMN);
	
	$_SESSION['startDate'] = unserialize($startdates);
	$_SESSION['endDate'] = unserialize($enddates);
	$_SESSION['desc'] = unserialize($descs);
	
	return $DBH->commit();
}

function viewEmploymentHistory ($id){
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	
	$q1 = $DBH->prepare("select StartDate from EmploymentHistory where ID=?");
	$q2 = $DBH->prepare("select EndDate from EmploymentHistory where ID=?");
	$q3 = $DBH->prepare("select Description from EmploymentHistory where ID=?");
	$q1->bindValue(1, $id);
	$q2->bindValue(1, $id);
	$q3->bindValue(1, $id);
	$q1->execute();
	$q2->execute();
	$q3->execute();
	
	$array = array (
			"startDates" => unserialize($q1->fetch(PDO::FETCH_COLUMN)),
			"endDates" => unserialize($q2->fetch(PDO::FETCH_COLUMN)),
			"descs" => unserialize($q3->fetch(PDO::FETCH_COLUMN))
			);
	$DBH->commit();
	
	return $array;
}

// Logs and reports a database error
function reportDBError ($exception) {
	$file = fopen("log.txt", "a");
	fwrite($file, date(DATE_RSS));
	fwrite($file, "\n");
	fwrite($file, $exception->getMessage());
	fwrite($file, $exception->getTraceAsString());
	fwrite($file, "\n");
	fwrite($file, "\n");
	fclose($file);
	require 'error.php';
	exit();
}
?>