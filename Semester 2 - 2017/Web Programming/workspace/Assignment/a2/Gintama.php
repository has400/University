<?php session_start(); ?>
<!DOCTYPE html>
<html>
    
<head>
<title>Gintama</title>
 <link rel='stylesheet' href='stylesheet.css' type='text/css' />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
</head>

<body>

<header>
<!-- Header -->
<div class="container">
        
</div>

<div class="item">
<center><img src="../img/cinemalogo.png" alt="Logo Of Silverado Cinema"></center>
</div>

</header>

<nav>
<!-- Navigation -->
<div class="container2">
    
<div class="table">
<ul>
  <li><a class="active" href="index.php">Home</a></li>
  <li><a href="showing.php">Now Showing</a></li>
  <li><a href="Contact.php">Contact Us</a></li>
</ul>
</div>

</div>
</nav>

<div class="left">
</div>

<div class="right">
</div>
 
<main>
<div class="container4">
                <center>

                    <div class="floatLeft">
                        <!-- sourced form http://www.hoyts.com.au/movies/2017/gintama.aspx -->
                        <img src="../img/gintama.jpg" height="300px" width="300px" border="1px">
                        <br>

                </center>

                <center>
                    <h3>GINTAMA</h3>
                    <br>
                </center>
             
                
                Gintama is an international movie straight from Japan, it is a live adaptation of the famous manga series of the same name. 
                </br>
                <!-- sourced from http://www.imdb.com/title/tt5805470/ -->
                <!-- sourced form http://www.hoyts.com.au/movies/2017/gintama.aspx -->
                Gintama is about a universe in which aliens exist and have successfully invaded and taken over feudal Tokyo, however there is a single human who is the last samurai. He takes on any challenge finding work however and where ever he can. </br>
                </br>
                </br>
                </br>
                </br>
                <center>
                 <?php include_once("../PHP/button.php"); ?>
                </center>
            </div>
        </div>
         </div>
    <br>
    </main>

    <footer
        <!-- Footer -->
        <div class="container2">
           <?php include_once("../PHP/footer.php"); ?>
            </div>
</footer>
<?php include_once("/home/eh1/e54061/public_html/wp/debug.php"); ?>
</body>
</html>
