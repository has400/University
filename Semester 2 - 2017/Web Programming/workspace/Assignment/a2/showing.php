<?php session_start(); 
?>
<!DOCTYPE html>

<html>
    
<head>
<title>Silverado Cinema</title>
 <link rel='stylesheet' href='stylesheet.css' type='text/css' />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Following script adapted from https://stackoverflow.com/questions/22441858/displaying-conditional-fields-in-html-using-a-form-select-menu -->
<script type="text/javascript">
  $(document).ready(function () {
    console.log("loaded");
    $('.CHsession').hide();
    $('.AFsession').hide();
    $('.RCsession').hide();
    $('.ACsession').hide();
  });

  function sessionSelector() {
    var selected = $("#movieSelection option:selected").val();
    document.getElementById("sessionSelection").disabled = false;
    document.getElementById("sessionSelection").value = "";
    console.log(selected + ' chosen');
 
    $('.CHsession').hide();
    $('.AFsession').hide();
    $('.RCsession').hide();
    $('.ACsession').hide();
    $('.' + selected + 'session').show();
  }
  
</script>
<!--end of adapted script -->
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
  <li><a href="Cart.php">View Cart</a></li>
</ul>
</div>

</div>
</nav>

<div class="left">
</div>

<div class="right">
</div>
<br>
 
<main>
  <div class="main">
  <div class="container3">
    <center>
      <div>
      <br>
       <h2>Now Showing!</h2>
        <br>
        <a href="../a2/BabyDriver.php">
        <img src="../img/BabyDriver.jpg" alt="Baby Driver">
        </a>
        <a href="../a2/DespicableMe3.php">
        <img src="../img/despicableme3.jpg" alt="Despicable Me 3">
        </a>
        <a href="../a2/Gintama.php">
        <img src="../img/gintama.jpg" alt="Gintama">
        </a>
        <a href="../a2/TheBigSick.php">
        <img src="../img/TheBigSick.jpg" alt="The Big Sick">
        </a>
        </div>
      </center>
      <center>
        <div>
          <h3>Pricing Information</h3>
          <table id="prices">
            <tr>
              <th>Seat Type</th>
              <th>Seat Option</th>
              <th>Seat Code</th>
              <th>Mon - Tue (All Day)<br>Mon - Fri (1pm only)</th>
              <th>Wed - Fri (After 1pm)<br>Sat - Sun (All Day)</th>
            </tr>
            <tr>
              <td>Standard</td>
              <td>Full</td>
              <td>SF</td>
              <td>$12.50</td>
              <td>$18.50</td>
            </tr>
            <tr>
              <td>Standard</td>
              <td>Concession</td>
              <td>SP</td>
              <td>$10.50</td>
              <td>$15.50</td>
            </tr>
            <tr>
              <td>Standard</td>
              <td>Child</td>
              <td>SC</td>
              <td>$8.50</td>
              <td>$12.50</td>
            </tr>
            <tr>
              <td>First Class</td>
              <td>Adult</td>
              <td>FA</td>
              <td>$25.00</td>
              <td>$30.00</td>
            </tr>
            <tr>
              <td>First Class</td>
              <td>Child</td>
              <td>FC</td>
              <td>$20.00</td>
              <td>$25.00</td>
            </tr>
            <tr>
              <td>Bean Bag</td>
              <td>Adult</td>
              <td>BA</td>
              <td>$22.00</td>
              <td>$33.00</td>
            </tr>
            <tr>
              <td>Bean Bag</td>
              <td>Family</td>
              <td>BF</td>
              <td>$20.00</td>
              <td>$30.00</td>
            </tr>
            <tr>
              <td>Bean Bag</td>
              <td>Child</td>
              <td>BC</td>
              <td>$20.00</td>
              <td>$30.00</td>
            </tr>
          </table>
          <h3>Session Times</h3>
          <table>
            <tr>
              <th>Times</th>
              <th>Mon - Tue</th>
              <th>Wed - Fri</th>
              <th>Sat - Sun</th>
            </tr>
            <tr>
              <td>12pm</td>
              <td></td>
              <td></td>
              <td class="ch">Despicable Me 3</td>
            </tr>
            <tr>
              <td>1pm</td>
              <td class="ch">Despicable Me 3</td>
              <td class="rc">The Big Sick</td>
              <td class="ch"></td>
            </tr>
            <tr>
              <td>2pm</td>
              <td class="ch"></td>
              <td class="rc"></td>
              <td></td>
            </tr>
            <tr>
              <td>3pm</td>
              <td ></td>
              <td></td>
              <td class="af">Gintama</td>
            </tr>
            <tr>
              <td>4pm</td>
              <td></td>
              <td></td>
              <td class="af"></td>
            </tr>
            <tr>
              <td>5pm</td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>6pm</td>
              <td class="af">Gintama</td>
              <td class="ch">Despicable Me 3</td>
              <td class="rc">The Big Sick</td>
            </tr>
            <tr>
              <td>7pm</td>
              <td class="af"></td>
              <td class="ch"></td>
              <td class="rc"></td>
            </tr>
            <tr>
              <td>8pm</td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td>9pm</td>
              <td class="rc">The Big Sick</td>
              <td class="ac">Baby Driver</td>
              <td class="ac">Baby Driver</td>
            </tr>
            <tr>
              <td>10pm</td>
              <td class="rc"></td>
              <td class="ac"></td>
              <td class="ac"></td>
            </tr>
            <tr>
              <td>11pm</td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </table>
          <br>
        </div>
        
        
<!-- Here's what our last "developer" left us, hope it helps! <i>- Silverado crew -->
  <!-- Starting form code sourced and adapted from https://titan.csit.rmit.edu.au/~e54061/wp/silverado-test.php -->
  <form action= "../PHP/addCart.php" method="post" id="form">
    <fieldset class="fieldset">
      <p>
        <h3>Booking Form</h3>
        <label>Movie</label>
        <select name="movie" required id="movieSelection" onchange="sessionSelector()">
          <option value ="">Please select a movie</option>
          <option value ="CH">DespicableMe3</option>
          <option value ="AC">Baby Driver</option>
          <option value ="AF">Gintama</option>
          <option value ="RC">The Big Sick</option>
        </select>
      </p>
      <p><label>Session</label>
        <select name="session" required id="sessionSelection" onchange="costCalc()">
          <option value ="">Please select a time</option>
          <option class ="CHsession" value ="MON-13">Monday 1pm</option>
          <option class ="CHsession" value ="TUE-13">Tuesday 1pm</option>
          <option class ="CHsession" value ="WED-18">Wednesday 6pm</option>
          <option class ="CHsession" value ="THU-18">Thursday 6pm</option>
          <option class ="CHsession" value ="FRI-18">Friday 6pm</option>
          <option class ="CHsession" value ="SAT-12">Saturday 12pm</option>
          <option class ="CHsession" value ="SUN-12">Sunday 12pm</option>
          
          <option class ="AFsession" value ="MON-18">Monday 6pm</option>
          <option class ="AFsession" value ="TUE-18">Tuesday 6pm</option>
          <option class ="AFsession" value ="SAT-15">Saturday 3pm</option>
          <option class ="AFsession" value ="SUN-15">Sunday 3pm</option>
          
          <option class ="RCsession" value ="MON-21">Monday 9pm</option>
          <option class ="RCsession" value ="TUE-21">Tuesday 9pm</option>
          <option class ="RCsession" value ="WED-13">Wednesday 1pm</option>
          <option class ="RCsession" value ="THU-13">Thursday 1pm</option>
          <option class ="RCsession" value ="FRI-13">Friday 1pm</option>
          <option class ="RCsession" value ="SAT-18">Saturday 6pm</option>
          <option class ="RCsession" value ="SUN-18">Sunday 6pm</option>
          
          <option class ="ACsession" value ="WED-21">Wednesday 9pm</option>
          <option class ="ACsession" value ="THU-21">Thursday 9pm</option>
          <option class ="ACsession" value ="FRI-21">Friday 9pm</option>
          <option class ="ACsession" value ="SAT-21">Saturday 9pm</option>
          <option class ="ACsession" value ="SUN-21">Sunday 9pm</option>
        </select>
      </p>
      <fieldset class="fieldset">
        <h4>Seats</h4>
        <fieldset class="fieldset">
          <h4>Standard <br> Sub Total: $ <span id ='standardTotal'> 0.00</span></h4>
          <p><label>Adult:</label>
          <select name="seats[SF]" id ="SFselector"onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          <p><label>Concession:</label>
          <select name="seats[SP]" id ="SPselector"onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          <p><label>Child:</label>
          <select name="seats[SC]" id ="SCselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>  
          </select></p>
        </fieldset>
        <fieldset class="fieldset">
          <h4>First Class <br>Sub Total: $ <span id ="firstClassTotal">0.00</span></h4>
          <p><label>Adult:</label>
          <select name="seats[FA]" id ="FAselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          <p><label>Child:</label>
          <select name="seats[FC]" id ="FCselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
        </fieldset>
        <fieldset class="fieldset">
          <h4>Bean Bags <br>Sub Total: $ <span id ="beanBagTotal">0.00</span></h4>
          <p><label>Adult:</label>
          <select name="seats[BA]" id ="BAselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          <p><label>Family:</label>
          <select name="seats[BF]" id ="BFselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          <p><label>Child:</label>
          <select name="seats[BC]" id ="BCselector" onchange="costCalc()">
            <option value ="0">0</option>
            <option value ="1">1</option>
            <option value ="2">2</option>
            <option value ="3">3</option>
            <option value ="4">4</option>
            <option value ="5">5</option>
            <option value ="6">6</option>
            <option value ="7">7</option>
            <option value ="8">8</option>
            <option value ="9">9</option>
          </select></p>
          
        </fieldset>
      </fieldset>
      <p>
        Total Price: $ <span id="totalCost">0.00</span>
        <br><br>
        <center>
        <button>Add to Cart</button> 
        </center>
      </p>
    </fieldset>
  </form>
  </center>

  <!-- End of Starting form code -->
  <br>
             <?php include_once("../PHP/plagiarise.php"); ?>

  </div>
  
  
</div>

    <br>
    </main>

    <footer
        <!-- Footer -->
        <div class="container2">
           <?php include_once("../PHP/footer.php"); ?>
            </div>
      


<script type="text/javascript">
  var standardSub = 0;
  var firstSub = 0;
  var beanSub = 0;
  var discounted = [
    "MON-13",
    "TUE-13",
    "MON-18",
    "TUE-18",
    "MON-21",
    "TUE-21",
    "WED-13",
    "THU-13",
    "FRI-13"
  ]
  
  function costCalc() {

    var SAselected = $("#SFselector option:selected").val();
    var SPselected = $("#SPselector option:selected").val();
    var SCselected = $("#SCselector option:selected").val();
    var session = $("#sessionSelection option:selected").val();
    if (discounted.indexOf(session) > -1)
    {
      standardSub = 12.5 * SAselected + 10.5 * SPselected + 8.5 * SCselected;
    }
    else
    {
      standardSub = 18.5 * SAselected + 15.5 * SPselected + 12.5 * SCselected;
    }
    var displayStandard = standardSub.toFixed(2);
    document.getElementById('standardTotal').innerHTML = displayStandard;

    var FAselected = $("#FAselector option:selected").val();
    var FCselected = $("#FCselector option:selected").val();
    var session = $("#sessionSelection option:selected").val();
    if (discounted.indexOf(session) > -1)
    {
      firstSub = 25 * FAselected + 20 * FCselected;
    }
    else
    {
      firstSub = 30 * FAselected + 25 * FCselected;
    }
    var displayFirst = firstSub.toFixed(2);
    document.getElementById('firstClassTotal').innerHTML = displayFirst;

    var BAselected = $("#BAselector option:selected").val();
    var BFselected = $("#BFselector option:selected").val();
    var BCselected = $("#BCselector option:selected").val();
    var session = $("#sessionSelection option:selected").val();
    if (discounted.indexOf(session) > -1)
    {
      beanSub = 22 * BAselected + 20 * BFselected + 20 * BCselected;
    }
    else
    {
      beanSub = 33 * BAselected + 30 * BFselected + 30 * BCselected;
    }
    var displayBean = beanSub.toFixed(2);
    document.getElementById('beanBagTotal').innerHTML = displayBean;
    
    
    var totalCost = standardSub + firstSub + beanSub;
    console.log(typeof(totalCost))
    var displayTotal = totalCost.toFixed(2);
    document.getElementById('totalCost').innerHTML = displayTotal;
  }                          
</script>
<?php include_once("/home/eh1/e54061/public_html/wp/debug.php"); ?>
</body>
</html>
