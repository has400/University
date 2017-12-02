<?php session_start(); ?>
<!DOCTYPE html>

<html>
    
<head>
<title>Silverado Cinema</title>
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
         <h4>Contact Us</h4>
     </center>
  

                    <div class="floatLeft"> 
                    <center>
                         <div id="map" style="width:50%;height:300px"></div>
<!-- this code was used from https://www.w3schools.com/graphics/google_maps_overlays.asp -->
<script>
function myMap() {
  var myCenter = new google.maps.LatLng(-37.244063, 144.452362);
  var mapCanvas = document.getElementById("map");
  var mapOptions = {center: myCenter, zoom: 10};
  var map = new google.maps.Map(mapCanvas, mapOptions);
  var marker = new google.maps.Marker({position:myCenter});
  marker.setMap(map);
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVfF1ze3mLmZaRPBudh5rpE9iSuVH3qW4&callback=myMap"></script>
             </center>  
             <center>
                 </br>
             Silderado cinema is located in Kynton Victoria, drop in at any time, our friendly staff will be happy to help you! Feel free to give us a call on 
             98458432 for any information about booking and other needs!
             </center>
             </div>
            </div>
        </div>
</body>
</<div>
    
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
