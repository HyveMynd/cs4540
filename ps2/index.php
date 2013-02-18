<?php 

//holds all the options for the dropdown menu
$options =	"<option value='#000000' selected=selected >Black</option>
				<option value='#FF0000'>Red</option>
				<option value='#FFFFFF'>White</option>
				<option value='#FFFF00'>Yellow</option>";

//holds all the images to be displayed
$images = "<td><img src='raptorsmall.jpg' /></td>
				<td><img src='kidsmall.jpg' /></td>
				<td><img src='yunosmall.jpg' /></td>
				<td><img src='dosxxsmall.jpg' /></td>";

$names[1] = "name=pic1";
$names[2] = "name=pic2";
$names[3] = "name=pic3";
$names[4] = "name=pic4";

//used to add html based on grouping
function AddRadioGroup ($num, $value){
	$check1 = "";
	$check2 = "";
	$check3 = "";
	$check4 = "";
	
	switch ($value){
		//find the stickied value
		case 'raptor.jpg':
			$check1 = 'checked';
			break;
		case 'kid.jpg':
			$check2 ='checked';
			break;
		case 'yuno.jpg':
			$check3 = 'checked';
			break;
		case 'dosxx.jpg':
			$check4 = 'checked';
			break;
	}
	return "<td><input type=radio name=$num value=raptor.jpg $check1 />Philosoraptor</td>
				<td><input type=radio name=$num value=kid.jpg $check2 />Success Kid</td>
				<td><input type=radio name=$num value=yuno.jpg $check3 />Y U NO</td>
				<td><input type=radio name=$num value=dosxx.jpg $check4 />Most Interesting Man</td>";
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src=MemeControl.js></script>
<link rel="stylesheet" href="MemeStyle.css" type="text/css" />
<title>Meme Generator</title>
</head>
<body>
	<form method=get action=YourMeme.php>
	<h1>Create your very own Meme!</h1>
	<h2>Meme 1</h2>
		<table>
			<tr>
				<td>Enter Top Caption</td>
				<td>Select Text Color</td>
			</tr>
			<tr>
				<td><input type="text" name=caption1 value="<?php echo $_GET['caption1'] ?>" /></td>
				<td>
					<select name=textColor1>
						<?php echo $options?>
					</select>
				</td>
			</tr>
			<tr><td>Select Picture</td></tr>
			<tr>
				<?php echo $images ?>
			</tr>
			<tr>
				<?php echo AddRadioGroup(pic1, $_GET['pic1'])?>
			</tr>
		</table>
		<h2>Meme 2</h2>
		<table>
			<tr>
				<td>Enter Top Caption</td>
				<td>Select Text Color</td>
			</tr>
			<tr>
				<td><input type="text" name=caption2 value="<?php echo $_GET['caption2']?>" /></td>
				<td>
					<select name=textColor2>
						<?php echo $options?>
					</select>
				</td>
			</tr>
			<tr><td>Select Picture</td></tr>
			<tr>
				<?php echo $images ?>
			</tr>
			<tr>
				<?php echo AddRadioGroup(pic2, $_GET['pic2'])?>
			</tr>
		</table>
		<h2>Meme 3</h2>
		<table>
			<tr>
				<td>Enter Top Caption</td>
				<td>Select Text Color</td>
			</tr>
			<tr>
				<td><input type="text" name=caption3 value="<?php echo $_GET['caption3']?>" /></td>
				<td>
					<select name=textColor3>
						<?php echo $options?>
					</select>
				</td>
			</tr>
			<tr><td>Select Picture</td></tr>
			<tr>
				<?php echo $images ?>
			</tr>
			<tr>
				<?php echo AddRadioGroup(pic3, $_GET['pic4'])?>
			</tr>
		</table>
		<h2>Meme 4</h2>
		<table>
			<tr>
				<td>Enter Top Caption</td>
				<td>Select Text Color</td>
			</tr>
			<tr>
				<td><input type="text" name=caption4 value="<?php echo $_GET['caption4'] ?>" /></td>
				<td>
					<select name=textColor4>
						<?php echo $options?>
					</select>
				</td>
			</tr>
			<tr><td>Select Picture</td></tr>
			<tr>
				<?php echo $images ?>
			</tr>
			<tr>
				<?php echo AddRadioGroup(pic4, $_GET['pic4'])?>
			</tr>
			<tr>
				<td><input type="button" onclick="validate();" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>