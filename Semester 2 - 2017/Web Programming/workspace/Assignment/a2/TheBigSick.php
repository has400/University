<?php session_start(); ?>
<!DOCTYPE html>

<html>
    
<head>
<title>The Big Sick</title>
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
                        <!-- This image was sourced from https://www.hoyts.com.au/movies/2017/the_big_sick.aspx-->
                        <img src="../img/TheBigSick.jpg" height="300px" width="300px" border="1px">
                        <br>

                </center>

                <center>
                    <h3>THE BIG SICK</h3>
                    <br>
                </center>
                The Big Sick is a romantic comedy for all ages, it is great movie to take your significant other to when planning a night out or a date.
                </br>
                <!-- sourced from http://www.imdb.com/title/tt5462602/ -->
                Kumail Nanjiani famous from tv shows such as Silicon Valley plays himself in a movie about meeting an American college student named Emily at one of his comedy shows. As they become more invested in their relationship, Kumail starts to worry about his Muslims parents and if they will accept her, even though she isn’t Muslim.</br>
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
