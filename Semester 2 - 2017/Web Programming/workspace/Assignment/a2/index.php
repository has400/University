<?php session_start(); ?>
<!DOCTYPE html>

<html>

<head>
    <title>Silverado Cinema</title>
    <link rel='stylesheet' href='stylesheet.css' type='text/css' />
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
</head>

<body>

    <header>
        <!-- Header -->
        <div class="container">

        </div>
        
        <div class="item">
            <center>
                <!-- We made this image, so it wasnt sourced from any where.-->
                <img src="../img/cinemalogo.png" alt="Logo Of Silverado Cinema">
            </center>
        </div>

    </header>

    <nav>
        <!-- Navigation -->
        <div class="container2">
<!-- the following code is from https://www.w3schools.com/css/css_navbar.asp -->
            <div class="table">
                <ul>
                    <li>
                        <a class="active" href="index.php">Home</a>
                    </li>
                    <li>
                        <a href="showing.php">Now Showing</a>
                    </li>
                    <li>
                        <a href="Contact.php">Contact Us</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>

    <div class="left">
    </div>

    <div class="right">
    </div>

    <main>
        <div class="main">

            <div class="container4">
                <center>

                    <div class="floatLeft">
                        <!-- This image was sourced from https://www.alpinehearingprotection.com/wp-content/uploads/2015/10/earplugs-cinema-hearing-protection_small.jpg-->
                        <img src="../img/cinema.jpg" height="300px" width="300px" border="1px">
                        <br>
                        <div class="floatRight">
                            <!-- This image was sourced from the design brief from the client, Silverado Cinema-->
                            <img src="../img/seatingplan.png" height="300px" width="300px" border="3px">
                        </div>
                        <div class="floatRight">
                            <!-- This image was sourced from  http://techpp.com/2012/09/04/top-3d-projectors/ -->
                            <br>
                            <br>
                            <img src="../img/3dprojection.jpg" height="300px" width="300px" border="3px">
                        </div>
                        <div class="floatRight">
                            <!-- This image was sourced from  https://www.dolby.com/us/en/platforms/dolby-cinema.html -->
                            <br>
                            <img src="../img/Dolby.png" height="300px" width="300px" border="3px">
                        </div>
                </center>

                <center>
                    <h1>The Grand Reopening Of Silverado!!!</h1>
                    <br>
                </center>
                Silverado has reopened up after months of renovations in order to be at the forefront of cinema technology, as a part of the rebuild process to provide the best service and experience we have installed a brand-new seating configuration, 3D projection and a Dolby sound system. Apart of these changes, we have made the ticket prices at a competitive rate, removing the need to travel long distances to go conventional cinemas.

                <br>
                <br>
                <br>
                <br>
                <b>Seating Arrangments</b>
                <br> The new seating configuration is an innovative and unique experience when visiting the Silverado Cinema, it offers customers the choice to pick where they want to sit and what they want to sit on! These seating arrangements are as follows;
                <br> 40 Normal seats, booking options:
                <br>
                <ul class="b">
                    <li>Full adult</li>
                    <br>
                    <li>Concession adult</li>
                    <br>
                    <li>Child under 12</li>
                    <br>
                </ul>
                <br> 12 First Class seats, booking options:
                <br>
                <ul class="b">
                    <li>Adult</li>
                    <br>
                    <li>Child under 12</li>
                    <br>
                </ul>
                <br> 13 Bean Bag seats, booking options:
                <br>
                <ul class="b">
                    <li>Adult (2 adults)</li>
                    <br>
                    <li>Family (1 adult + 1 child)</li>
                    <br>
                    <li>Child (3 children under 12)</li>
                    <br>
                </ul>
                <br>
                <br>
                <br>
                <b>3D Projection</b>
                <br> As a part of the ongoing efforts of creating the best possible cinematic viewing place in the area, we now have the capabilities to show 3D movies to the screen. Be ready to be fully immersed in a movie and experience something truly remarkable experience.
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <b>Dolby Sound and Lighting</b>
                <br> The state of the art Dolby Sound and lighting package simply put, is amazing, it adds to the new frontier of going to the cinema. The sound technology from Dolby allows for a powerful collection of speakers to create an audio environment which mimics real life. Speakers are placed around the room to produce the sounds of the movie in the places they are happening, whether that is behind or on the side of the viewer. The new lighting setup also allows for a dynamic setup within the cinema.
                </div>
            </div>
        </div>

        <br>
        <center>
            <div class="container3">
                <h4>Now Showing! </h4>
                <br>
                <!-- This image was sourced from https://www.hoyts.com.au/movies/2017/baby_driver.aspx-->
                <a href="../a2/BabyDriver.php">
                    <img src="../img/BabyDriver.jpg" alt="Baby Driver">
                </a>
                <!-- This image was sourced from http://www.hoyts.com.au/movies/2017/despicable_me_3.aspx-->
                <a href="../a2/DespicableMe3.php">
                    <img src="../img/despicableme3.jpg" alt="Despicable Me 3">
                </a>
                     <!-- sourced form http://www.hoyts.com.au/movies/2017/gintama.aspx -->
                <a href="../a2/Gintama.php">
                    <img src="../img/gintama.jpg" alt="Gintama">
                </a>
                 <!-- This image was sourced from https://www.hoyts.com.au/movies/2017/the_big_sick.aspx-->
                <a href="../a2/TheBigSick.php">
                    <img src="../img/TheBigSick.jpg" alt="The Big Sick">
                </a>
                
              <?php include_once("../PHP/plagiarise.php"); ?>
              
            </div>
            
            </div>
          
        </center>
         <br>
    </main>

    <footer
        <!-- Footer -->
        <div class="container2">
            <?php include_once("../PHP/footer.php"); ?>
            </div>
    </footer
    
<?php include_once("/home/eh1/e54061/public_html/wp/debug.php"); ?>

</body>

</html>