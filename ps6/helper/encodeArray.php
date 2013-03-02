<script type="text/javascript">
<?php
$startjs = json_encode($start);
$endjs = json_encode($end);
$descjs = json_encode($desc);
echo "var startArray = ". $startjs . ";\n";
echo "var endArray = ".$endjs . ";\n";
echo "var descArray = ".$descjs . ";\n";
?>
$('document').ready( function(){
	populateFields(<?php echo count($_SESSION["startDate"]) ?>);
	attachRemove();
});
</script>