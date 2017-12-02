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
         <h4>Cart</h4>
     </center>
  
        <?php
    $sizeofarray = sizeof($_SESSION['cart']);
    
    $ScreenTime = array("MON-13"=>"Monday, 1pm","TUE-13"=>"Tuesday, 1pm", "WED-18"=>"Wednesday, 6pm", "THU-18"=>"Thursday, 6pm", "FRI-18"=>"Friday, 6pm", "SAT-12"=>"Saturday, 12pm", "SUN-12"=>"Sunday, 12pm",
    "MON-18"=>"Monday, 6pm", "TUE-18"=>"Tuesday, 6pm", "SAT-15"=>"Saturday, 3pm", "SUN-15"=>"Sunday, 3pm",
    "MON-21"=>"Monday, 9pm", "TUE-21"=>"Tuesday, 9pm", "WED-13"=>"Wednesday, 1pm", "THU-13"=>"Thursday, 1pm", "FRI-13"=>"Friday, 1pm", "SAT-18"=>"Saturday, 6pm","SUN-18"=>"Sunday, 6pm",
    "WED-21"=>"Wednesday, 9pm", "THU-21"=>"Thursday, 9pm", "FRI-21"=>"Friday, 9pm", "SAT-21"=>"Saturday, 9pm", "SUN-21"=>"Sunday, 9pm");
    $discounted = array(
    "MON-13",
    "TUE-13",
    "MON-18",
    "TUE-18",
    "MON-21",
    "TUE-21",
    "WED-13",
    "THU-13",
    "FRI-13"
  );
  $grandtotal = 0;
     echo "<table class=\"table2\">";
     for ($x = 0; $x <= sizeof($_SESSION['cart']); $x++) {
         
     echo '<script>console.log('.$x.')</script>';
          if (empty($_SESSION['cart'][$x])){
         continue;
       }
       
       echo "<tr>";
        echo "<td>";
        if ($_SESSION['cart'][$x][movie] == "AC"){
        echo "<h2>Baby Driver</h2><br>";
        echo "Showing at ";
        echo $ScreenTime[$_SESSION['cart'][$x][session]];
      }else if ($_SESSION['cart'][$x][movie] == "CH"){
        echo "<h2>Despicable Me 3</h2><br>";
        echo "Showing at ";
        echo $ScreenTime[$_SESSION['cart'][$x][session]];
      }else if ($_SESSION['cart'][$x][movie] == "AF"){
        echo "<h2>Gintama</h2><br>";
        echo "Showing at ";
       echo $ScreenTime[$_SESSION['cart'][$x][session]];
      }else if ($_SESSION['cart'][$x][movie] == "RC"){
        echo "<h2>The Big Sick<br></h2>";
        echo "Showing at ";
        echo $ScreenTime[$_SESSION['cart'][$x][session]];
      }
      

           echo "<table class=\"table2\">";
       
    echo '<tr style="text-align:left;">';
      echo "<th>Ticket Type</th>";
      echo "<th>Cost</th>";
      echo "<th>Qty</th>";
      echo "<th>Subtotal</th>";
    echo "</tr>";
    
      $total = 0;
      $price = 0;
    for ($i = 0; $i <= 7; $i++) {
            if ($i == 0 && ($_SESSION['cart'][$x][seats]["SF"] != 0)){
            echo "<tr>";
               
                echo "<td>";
                echo "Standard (Full)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 12.50;
                  
                } else{
                  $price = 18.50;
                }
                
                 echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SF"];
                echo "</td>";
                
                 echo "<td>";
                echo "$". $price * $_SESSION['cart'][$x][seats]["SF"];
                $total += $price * $_SESSION['cart'][$x][seats]["SF"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 1 && ($_SESSION['cart'][$x][seats]["SP"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "Standard (Concession)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 10.50;
                  
                } else{
                  $price = 15.50;
                }
                
                 echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SP"];
                echo "</td>";
                
                 echo "<td>";
               echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["SP"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["SP"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 2 && ($_SESSION['cart'][$x][seats]["SC"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "Standard (Child)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 8.50;
                  
                } else{
                  $price = 12.50;
                }
                
                echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SC"];
                echo "</td>";
                
                 echo "<td>";
               echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["SC"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["SC"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 3 && ($_SESSION['cart'][$x][seats]["FA"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "First Class (Adult)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 25.00;
                  
                } else{
                  $price = 30.00;
                }
                
                 echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["FA"];
                echo "</td>";
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["FA"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["FA"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 4 && ($_SESSION['cart'][$x][seats]["FC"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "First Class (Child)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 25.00;
                }
                
                echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["FC"];
                echo "</td>";
                
                 echo "<td>";
               echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["FC"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["FC"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 5 && ($_SESSION['cart'][$x][seats]["BA"] != 0)){
               echo "<tr>";
               
                echo "<td>";
                echo "Beanbag (Adult)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 22.00;
                  
                } else{
                  $price = 33.00;
                }
                
                 echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BA"];
                echo "</td>";
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BA"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["BA"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 6 && ($_SESSION['cart'][$x][seats]["BF"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "Beanbag (Family)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 30.00;
                }
                
                echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BF"];
                echo "</td>";
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BF"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["BF"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 7 && ($_SESSION['cart'][$x][seats]["BC"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo "Beanbag (Child)";
                echo "</td>";
                
                echo "<td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 30.00;
                }
                
                echo "$".number_format((float)$price, 2, '.', '');
                echo "</td>";
                
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BC"];
                echo "</td>";
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BC"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["BC"];
                echo "</td>";
                
            echo "</tr>";
            }
    }
    
                echo "<tr>";
          
                echo "<td>";
              
                echo "</td>";
                
                echo "<td>";
                
                echo "</td>";
                
             echo '<td style="text-align:right;">';
                echo "Total: ";
                echo "</td>";
                
                echo '<td style="text-align:left;">';
                 echo "$".number_format((float)$total, 2, '.', '');
                echo "</td>";
                
            
                
                $grandtotal += $total;
            echo "</tr>";
    
    echo "</table>";
     echo "</tr>";
     }
echo "<table class=\"table2\">";
        echo "<tr>";
    
                echo '<td style="text-align:right;">';
                   echo "Grand Total: $".number_format((float)$grandtotal, 2, '.', '');
                echo "</td>";
     
        echo "</tr>";
   echo "</center>";
echo "</table>";
     echo "</table>";
     
?>
<br>
<form action="welcome.php" method="post">
      <fieldset>
        <legend>Customer Details</legend>
        <p>
          <label>Name: </label>
          <!-- pattern below from tute 08 answers -->
          <input type = "text"
                 name="name"
                 value = "" 
                 pattern ="^[a-zA-Z \-.']+$">
        </p>
        <p>
        <!-- pattern below modified from tute 08 answers -->
          <label>Phone: </label>
          <input type = "text"
                 name="phone"
                 value = "" 
                 pattern ="^(\(04\)|04|\+61[ ]?4)([ ]?\d){8}$"/>
        </p>
        <p>
          <label>Email: </label>
          <!-- pattern below from w3schools https://www.w3schools.com/tags/att_input_pattern.asp -->
          <input type = "email"
                 name="email"
                 value = "" 
                 pattern ="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"/>
        </p>
        <input type="submit">
      </fieldset
    </form>


<?php include_once("../PHP/plagiarise.php"); ?>
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
