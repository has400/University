<?php session_start(); ?>
<!DOCTYPE html>

<html>
    
<head>
<title>Baby Driver</title>
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
                        <!-- This image was sourced from https://www.hoyts.com.au/movies/2017/baby_driver.aspx-->
                        <img src="../img/BabyDriver.jpg" height="300px" width="300px" border="1px">
                        <br>

                </center>

                <center>
                    <h3>BABY DRIVER</h3>
                    <br>
                </center>
             
                  Baby driver is now being shown at the Silverado cinemas, it is one of the hottest actions movies currently in theaters, come on down to see it!
                </br>
                <!-- sourced from http://austin.violetcrown.com/movieinfo.php?movie=HO00000817 -->
               Baby driver is about a getaway driver who relies heavily on his favorite song to be one of the best heist getaway drivers. When the crime boss involves the young driver in an assignment which is doomed to fail, he must rely on all his skills to protect his life and his new girlfriend.</br>
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
<?php include_once("/home/eh1/e54061/public_html/wp/debug.php"); ?>
</body>
</html>
