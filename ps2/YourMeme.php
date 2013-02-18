<html>
<head>	
<link rel="stylesheet" href="MemeStyle.css" type="text/css" />
</head>
<body>
<table class=memePics>
	<tr>
		<td background=<?php echo $_GET['pic1']?> style=color:<?php echo $_GET['textColor1']?>><?php echo $_GET['caption1']?></td>
		<td background=<?php echo $_GET['pic2']?> style=color:<?php echo $_GET['textColor2']?>><?php echo $_GET['caption2']?></td>
	</tr>
	<tr>
		<td background=<?php echo $_GET['pic3']?> style=color:<?php echo $_GET['textColor3']?>><?php echo $_GET['caption3']?></td>
		<td background=<?php echo $_GET['pic4']?> style=color:<?php echo $_GET['textColor4']?>><?php echo $_GET['caption4']?></td>
	</tr>
</table>
</body>
</html>