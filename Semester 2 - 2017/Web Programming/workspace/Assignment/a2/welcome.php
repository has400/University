<?php session_start(); 
?>
<!DOCTYPE html>

<html>
  <head>
    <title>
      Invoice
    </title>
    <style type="text/css">
    .invoice-box {
        max-width: 800px;
        margin: auto;
        padding: 30px;
        border: 1px solid #eee;
        box-shadow: 0 0 10px rgba(0, 0, 0, .15);
        font-size: 16px;
        line-height: 24px;
        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
        color: #555;
    }

    .invoice-box table {
        width: 100%;
        line-height: inherit;
        text-align: left;
    }

    .invoice-box table td {
        padding: 5px;
        vertical-align: top;
    }

    .invoice-box table tr td:nth-child(2) {
        text-align: right;
    }

    .invoice-box table tr.top table td {
        padding-bottom: 20px;
    }

    .invoice-box table tr.top table td.title {
        font-size: 45px;
        line-height: 45px;
        color: #333;
    }

    .invoice-box table tr.information table td {
        padding-bottom: 40px;
    }

    .invoice-box table tr.heading td {
        background: #eee;
        border-bottom: 1px solid #ddd;
        font-weight: bold;
    }

    .invoice-box table tr.details td {
        padding-bottom: 20px;
    }

    .invoice-box table tr.item td{
        border-bottom: 1px solid #eee;
    }

    .invoice-box table tr.item.last td {
        border-bottom: none;
    }

    .invoice-box table tr.total td:nth-child(2) {
        border-top: 2px solid #eee;
        font-weight: bold;
    }

    @media only screen and (max-width: 600px) {
        .invoice-box table tr.top table td {
            width: 100%;
            display: block;
            text-align: center;
        }
        
        .invoice-box table tr.information table td {
            width: 100%;
            display: block;
            text-align: center;
        }
    }

    /*]]>*/
    </style>
  </head>
  <body>
    <div class="invoice-box">
      <table cellpadding="0" cellspacing="0">
        <tr class="top">
          <td colspan="2">
            <table>
              <tr>
                <td class="title">
                  <img src="../img/cinemalogo.png" alt=
                  "Logo Of Silverado Cinema" style=
                  "width:100%; max-width:300px;" />
                </td>
                <td>
                  <script language="javascript" type="text/javascript">
                  var today = new Date();
                  document.write(today);
                  //]]>
                  </script>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr class="information">
          <td colspan="2">
            <table>
              <tr>
                <td>
                  <?php echo $_POST["name"]; ?><br />
                  <?php echo $_POST["phone"]; ?><br />
                  <?php echo $_POST["email"]; ?><br />
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr class="heading">
          <td>
            Movie Name
          </td>
          <td>
            Price
          </td>
        </tr>
        
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
                  
                     for ($x = 0; $x <= sizeof($_SESSION['cart']); $x++) {
                          if (empty($_SESSION['cart'][$x])){
                         continue;
                       }
                       
                       echo "<tr class=\"item\">";
                       
                       echo "<td>";
                       
                        if ($_SESSION['cart'][$x][movie] == "AC"){
                        echo "Baby Driver, ";
                        echo "Showing on ";
                        echo $ScreenTime[$_SESSION['cart'][$x][session]];
                      }else if ($_SESSION['cart'][$x][movie] == "CH"){
                        echo "Despicable Me 3, ";
                        echo "Showing on ";
                        echo $ScreenTime[$_SESSION['cart'][$x][session]];
                      }else if ($_SESSION['cart'][$x][movie] == "AF"){
                        echo "Gintama, ";
                        echo "Showing on ";
                       echo $ScreenTime[$_SESSION['cart'][$x][session]];
                      }else if ($_SESSION['cart'][$x][movie] == "RC"){
                        echo "The Big Sick, ";
                        echo "Showing on ";
                        echo $ScreenTime[$_SESSION['cart'][$x][session]];
                      }
                        
    echo "<table class=\"table2\">";
           
      $total = 0;
      $price = 0;
      
    for ($i = 0; $i <= 7; $i++) {
            if ($i == 0 && ($_SESSION['cart'][$x][seats]["SF"] != 0)){
            echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SF"]."x Standard (Full)";
                echo "</td>";
                

                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 12.50;
                  
                } else{
                  $price = 18.50;
                }
            
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["SF"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["SF"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 1 && ($_SESSION['cart'][$x][seats]["SP"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SP"]."x Standard (Concession)";
                echo "</td>";
                
        
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 10.50;
                  
                } else{
                  $price = 15.50;
                }
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["SP"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["SP"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 2 && ($_SESSION['cart'][$x][seats]["SC"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["SC"]."x Standard (Child)";
                echo "</td>";
                
             
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 8.50;
                  
                } else{
                  $price = 12.50;
                }
                
                
                 echo "<td>";
               echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["SC"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["SC"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 3 && ($_SESSION['cart'][$x][seats]["FA"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["FA"]."x First Class (Adult)";
                echo "</td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 25.00;
                  
                } else{
                  $price = 30.00;
                }
            
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["FA"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["FA"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 4 && ($_SESSION['cart'][$x][seats]["FC"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["FC"]."x First Class (Child)";
                echo "</td>";
                
              
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 25.00;
                }
               
                 echo "<td>";
               echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["FC"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["FC"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 5 && ($_SESSION['cart'][$x][seats]["BA"] != 0)){
               echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BA"]."x Beanbag (Adult)";
                echo "</td>";
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 22.00;
                  
                } else{
                  $price = 33.00;
                }
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BA"], 2, '.', '');;
                $total += $price * $_SESSION['cart'][$x][seats]["BA"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 6 && ($_SESSION['cart'][$x][seats]["BF"] != 0)){
                echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BF"]."x Beanbag (Family)";
                echo "</td>";
                
            
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 30.00;
                }
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BF"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["BF"];
                echo "</td>";
                
            echo "</tr>";
            }else if ($i == 7 && ($_SESSION['cart'][$x][seats]["BC"] != 0)){
            echo "<tr>";
               
                echo "<td>";
                echo $_SESSION['cart'][$x][seats]["BC"]."x Beanbag (Child)";
                echo "</td>";
                
               
                
                if (in_array($_SESSION['cart'][$x][session], $discounted)){
                  $price = 20.00;
                  
                } else{
                  $price = 30.00;
                }
                
                 echo "<td>";
                echo "$".number_format((float)$price * $_SESSION['cart'][$x][seats]["BC"], 2, '.', '');
                $total += $price * $_SESSION['cart'][$x][seats]["BC"];
                echo "</td>";
            echo "</tr>";   
            }
        
    }
   
             $grandtotal += $total;
    echo "</td>";
    
 echo "</table>";
  echo "<td>";
  echo "Total: $".number_format((float)$total, 2, '.', '');
  echo "</td>";
    echo "</tr>";
     }
    
 echo "<tr class=\"total\">";
           echo "<td></td>";
           echo "<td>";
           echo "Grand Total: $".number_format((float)$grandtotal, 2, '.', '');
           echo "</td>";
         echo "</tr>";
?>
      
          
      
        
      </table>
    </div>
  </body>
</html>