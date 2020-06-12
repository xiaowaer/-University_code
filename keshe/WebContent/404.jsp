<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>404</title>
<style>
      @import 'https://fonts.googleapis.com/css?family=Secular+One|Source+Sans+Pro';
body,
html {
  margin: 0;
  height: 100%;
  overflow:hidden;
}

main {
  display: flex;
  height: 100%;
  text-align: center;
  justify-content: center;
  align-items: center;
  background: #87CEFA;
  color: white;
  text-shadow:1px 1px 2px rgba(0,0,0,0.2);
  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

h1 {
  font-size: 5em;
  font-family: 'Secular One', sans-serif;
  margin: 0;
}
p{
  font-family: 'Source Sans Pro', sans-serif;
  margin:0;
}
.cloud {
	width: 175px; height: 60px;
	
	background: white;
	
	border-radius: 100px;
	
	position: absolute;
  z-index:5;
  top:35%;
  left:10%;
}
.cloud.bt{
  top:75%;
  left:75%;
}

.cloud:after, .cloud:before {
	content: '';
	position: absolute;
	background: white;
	z-index: -1
}

.cloud:after {
	width: 50px; height: 50px;
	top: -25px; left: 25px;
	
	border-radius: 50%;
}

.cloud:before {
	width: 90px; height: 90px;
	top: -45px; right: 25px;
	
border-radius:200px;
}


</style>

</head>
<body translate="no">
<main>
<div class="content">
<h1>404</h1>

</div>
</main>
<div class="cloud"></div>
<div class="cloud bt"></div>

</body>
</html>

