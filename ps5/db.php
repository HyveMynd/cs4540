<?php
require 'hidden/dbpassword.php';

// Opens and returns a DB connection
function openDBConnection () {
	global $dbpassword;
	$DBH = new PDO("mysql:host=atr.eng.utah.edu;dbname=ps4_monroy",
			'monroy_sw', $dbpassword);
	$DBH->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $DBH;
}

function storeResume ($resumeName, $name, $phone, $addr, $position, $startdate, $enddate, $desc){
// 	echo $name.$phone.$addr.$position;
	
	try{
		//get id
		$id = getResumeID($resumeName);
				
		//store contact info
		storeContactInfo($id, $name, $phone, $addr);
		
		//store position indo
		storePositionSought($id, $position);
		
		//store employment history
		//storeEmploymentHistory($id, $startdate, $enddate, $desc);
		
	}
	catch (PDOException $e){
		if ($e->getCode() == 23000) {
			return false;
		}
		reportDBError($e);
	}
}

function getResumeID ($resumeName){
	//get the ResumeID
	$DBH = openDBConnection();
	$DBH->beginTransaction();
	$stmt = $DBH->prepare("select * from ResumeNames where ResumeName=?");
	$stmt->bindValue(1, $resumeName);
	$stmt->execute();
	
	//create resume if not in database
	if ($stmt->rowCount() <= 0){
		$insert = true;
		$query2 = $DBH->prepare("insert into ResumeNames (ResumeName) values (?)");
		$query2->bindValue(1, $resumeName);
		$query2->execute();
		$stmt->execute();
	}
	
	$row = $stmt->fetch();
	$DBH->commit();
	return $row['ResumeID'];
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
	$stmt = $DBH->prepare("insert into EmploymentHistory (ID, StartDate, EndDate, Description) values (?,?,?,?)");
	$stmt->bindValue(1, $id);
	$stmt->bindValue(2, $startdate);
	$stmt->bindValue(3, $enddate);
	$stmt->bindValue(4, $desc);
	$stmt->execute();
	return $DBH->commit();
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