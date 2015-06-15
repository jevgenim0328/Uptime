<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
<script src="./scripts/ajax-utils.js"
        type="text/javascript"></script>
<script src="./scripts/output-utils.js"
        type="text/javascript"></script>
</head>
<body>

<div id="wrapper">
	<div id="header">
	</div><!-- header -->
	<div id="content">

	<table border="1">
  	<tr><th class="title">Kauba otsimine amazon.com kaupluses</th></tr>
	</table>
	<p/>
	<fieldset>	
	<legend>Otsingusõna sisestus</legend>
	<label>
	  <input type="text" id="sisestus"/> 
	  </label>
	  <input type="button" value="Otsi" onclick='showData("sisestus","goods-list-result")' /> 
     <div id="goods-list-result"></div>
     </fieldset>         
	  
	<p/>
	</div><!-- content -->
	<div id="footer">
	<center>Jevgeni Mihhailov, e-post: jevgenim0328@gmail.com</center>
	<br>
	<a href=test>Test </a>
	</div><!-- footer -->

</div><!-- #wrapper -->
</body>

</html>