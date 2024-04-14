<!DOCTYPE html>
<html>
<% int avl=(int) request.getAttribute("AVL"); %>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Railway Reservation System</title>
<style>
 
body, h1, h2, h3, p, ul, li, form, input, button {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: cursive;
    background-color: #f3f3f3;
    color: #333;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}


.main {
    padding: 50px 0;
   
}

.journey-details {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin-bottom: 20px;
}

.journey-details h2 {
    font-size: 20px;
    margin-bottom: 10px;
}

.journey-info {
    display: flex;
    justify-content: space-between;
}

.train-info {
    flex: 1;
}

.train-info h3 {
    font-size: 18px;
    margin-bottom: 10px;
}

.ticket-price {
    flex: 1;
    text-align: right;
}

.passenger-section {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
     border:none;
}

.passenger-section h2 {
    font-size: 20px;
    margin-bottom: 10px;
}

.passenger-form {
    margin-bottom: 20px;
}

.passenger-form form {
    display: flex;
    flex-wrap: wrap;
}

.passenger-form input,
.passenger-form label {
    margin-bottom: 10px;
}

.passenger-form input[type="text"],
.passenger-form input[type="number"] {
    width: 30%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.passenger-form label {
    flex: 1;
    margin-right: 10px;
}

.passenger-form button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.passenger-form button:hover {
    background-color: #0056b3;
}

.passenger-list ul {
    list-style-type: none;
    padding: 0;
}

.passenger-list li {
    border-bottom: 1px solid #ccc;
    padding: 10px 0;
}
.book
{
border:1px solid black;
color:white;
width:20%;
margin-top: 20px;
background-color: #007bff;
border: none;
border-radius: 4px;
cursor: pointer;
padding: 10px 20px;
margin-left:40%;
}


</style>
<script>
var i=0;
function addPassenger() {
    var passengerList = document.getElementById("passengers");
    i++;
    var name = document.getElementById("passengerName").value;
    var age = document.getElementById("passengerAge").value;
    var genderInput = document.querySelector('input[name="gender"]:checked');
    var gender = genderInput ? genderInput.value : '';
    var avl=<%= avl %>;
    if (passengerList.children.length < avl) {
    	console.log(avl +"gyuhjik");
    	console.log(passengerList.children.length);
    	var listItem = document.createElement("li");
    	listItem.setAttribute("name"+i, name); 
    	listItem.setAttribute("age"+i, age); 
    	listItem.setAttribute("gender"+i, gender);
    listItem.innerHTML = "<strong >Name:</strong> " + name + ", <strong>Age:</strong> " + age + ", <strong>Gender:</strong> " + gender;
    passengerList.appendChild(listItem);
    document.getElementById("passengerName").value = "";
    document.getElementById("passengerAge").value = "";
    document.getElementById("male").checked = false;
    document.getElementById("female").checked = false;
    document.getElementById("other").checked = false;
    }
    else {
        alert("You can only add up to " + avl + " passengers.");
    }
    
}


</script>
</head>
<body>



<main class="main">
    <div class="container">
        <section class="journey-details">
            <h2>Journey Details</h2>
            <div class="journey-info">
                <div class="train-info">
                    <h3><%= request.getAttribute("TrainName") %> (<%= request.getAttribute("TrainNum") %>)</h3>
                    <p><strong>From:</strong> <%= request.getAttribute("From") %></p>
                    <p><strong>To:</strong> <%= request.getAttribute("To") %></p>
                    <p><strong>Arrival:</strong> <%= request.getAttribute("ArrivDate") %> <%= request.getAttribute("ArrivDay") %></p>
                    <p><strong>Departure:</strong> <%= request.getAttribute("DepDate") %> <%= request.getAttribute("DepDay") %></p>
                </div>
                <div class="ticket-price">
                    <h3>Ticket Price</h3>
                    <p>$<span id="ticketPrice">100</span></p>
                </div>
            </div>
        </section>
        
        <section class="passenger-section">
            <h2>Passengers</h2>
            <div class="passenger-form" id="passengerForm">
            
                <h3>Add Passenger</h3>
                <% int i=0; %>
                <form>
                   <%i++; %>
                    <input type="text" id="passengerName" placeholder="Name"name="passengerName[<%=i %>]" required>
                    <input type="number" id="passengerAge" placeholder="Age" name="passengerAge[<%=i %>]" required>
                    <label for="gender">Gender:</label>
                    <input type="radio" id="male" name="gender" value="male" name="gender[<%=i %>]" required>
                    <label for="male">Male</label>
                    <input type="radio" id="female" name="gender" value="female" name="gender[<%=i %>]" required>
                    <label for="female">Female</label>
                    <input type="radio" id="other" name="gender" value="other" name="gender[<%=i %>]" required>
                    <label for="other">Other</label>
                    <button type="button" onclick="addPassenger()">Add Passenger</button>
                  
                </form>
            </div>
            
            <div class="passenger-list" id="passengerList">
                <h3>Passenger List</h3>
                <ul id="passengers"></ul>
            </div>
        </section>
    </div>
    <form action="Book" method="post">
    <input type="submit" value="BOOK" class="book">
    </form>
</main>
</body>
</html>

