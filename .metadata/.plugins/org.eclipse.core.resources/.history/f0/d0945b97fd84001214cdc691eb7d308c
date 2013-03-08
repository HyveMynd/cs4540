<?php
class resumeSite {
	function CheckLogin(){
		if(!isset($_SESSION)){ session_start(); }
		
		if(isset($_SESSION['login'])){
			return true;
		}
		return false;
	}
	
	function Login(){
		$login = $_POST['loginName'];
		$password = $_POST['loginPass'];
		if($this->CheckLoginInDB($login, $password))
			return true;
		else
			return false;
	}
	
	function Logout(){
		session_unset();
		$this->RedirectToURL("../Views/contactInfo.php");
	}
	
	function SetPageOrigin(){
		session_start();
		if(isset($_SERVER['HTTP_REFERER']))
			$_SESSION['origin'] = $_SERVER['HTTP_REFERER'];
		else{
			$_SESSION['origin'] = $_SESSION['destination'];
		}
	}
	
	function RedirectToOrigin(){
		session_start();
		$this->RedirectToURL($_SESSION['origin']);
	}
	
	function RegisterUser(){
		require '../database/db.php';
		$realName = $_REQUEST['fullName'];
		$login = $_REQUEST['login'];
		$password = $_REQUEST['password'];
		$role = "client";
		if(GetUserID($login) > 0)
			return false;
		else{
			$this->SetRole($role);
			return CreateNewUser($realName, $login, $password, $role);
		}
	}
	
	function CheckAdmin(){
		if(isset($_SESSION))
			if($_SESSION['role'] == "admin")
				return true;
		return false;
	}
	
	function CheckLoggedIn(){
		if(isset($_SESSION))
			if(isset($_SESSION['login']))
			return true;
		return false;
	}
	
	function SetRole($role){
		if(!isset($_SESSION))
			session_start();
		$_SESSION['role'] = $role;
	}
	
	function RedirectToURL($url){
		header("Location: $url");
		exit;
	}
	
	function SaveUserName($login){
		if(!isset($_SESSION))
			session_start();
		$_SESSION['login'] = $login;
	}
	
	function CheckLoginInDB($login, $password){
		session_start();
		require '../database/db.php';
		$badLogin = false;
		$userInfo = CheckValidCredentials($login, $password);
		if ($userInfo == null)
			return false;
		$this->SaveUserName($userInfo['Login']);
		$this->SetRole($userInfo['Role']);
		$this->SetName($userInfo['RealName']);
		return true;
	}
	
	function SetName($fullName){
		if(!isset($_SESSION))
			session_start();
		$_SESSION['fullName'] = $fullName;
	}
	
	function GetUsers(){
		require_once('../database/db.php');
		return AllUsers();
	}
	
	function ChangeRole($uid){
		require_once('../database/db.php');
		ChangeRole($uid);
	}
	
	function DeleteUser($uid){
		require_once('../database/db.php');
		DeleteUser($uid);	
	}
}

?>