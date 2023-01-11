
const url = 'http://localhost:8080/allusers/'

const loadDataSimple = () => {
    fetch(url)
        .then( response => { 
            if( response.ok ){
                console.log( response );
                return response.json(); 
            } else {
                console.log( "Got error. Status : " + response.status ) 
            }

        })
        .then( data => createListFromJson(data));
}

const createListFromJson = (arr) => {
    //inner from json
    arr.forEach(element => {
		console.log(element.name);
});
}


const loadDataAsync = async () => {
    try{
        console.log( "Kraunam duomenis");
        const response = await fetch (url);
        const data = await response.json();
        showData( data );
    } catch ( error ){
        console.error( error );
    }
} 

const loadMultiData = async () =>{
    const url1 = 'https://reqres.in/api/users?page=1'
    const url2 = 'https://reqres.in/api/users?page=2'
    const url3 = 'https://reqres.in/api/users?page=3'

    const responses = await Promise.all( [ 
        fetch(url1), 
        fetch(url2), 
        fetch(url3) 
    ]);

    const multiPromises = responses.map( r =>  r.json() );
    const finalData = await Promise.all( multiPromises );
    processData( finalData );
}

const processData = (arr) => {
    //processing arr as data
}


const showData = (data) => {
    console.log(data);
}

function setColor(){
	document.getElementById("headerSearch").style.backgroundColor = "rgba(249, 249, 249, 1)"
	
	

}

const setColor2 = () => {
		document.getElementById("headerSearch").style.backgroundColor = "rgba(240, 240, 240, 1)"
}

const wordColor = () => {
	if((document.URL).includes("/word")){
		document.getElementById("word").style.backgroundColor = "rgba(225, 225, 225, 1)";
		console.log("žaliuzės")
	}
}

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("myDropdown");
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

var xes = document.getElementsByClassName('mybutton');
console.log("surasti objektai mybutton: " + xes.length)

var xxxx = document.getElementsByClassName('xxx');
console.log("surasti objektai mybutton: " + xxxx.length)

var test = document.getElementById('test');
console.log(test)

var colors = ['rgb(217, 236, 234)','rgb(242, 202, 200)','rgb(192, 201, 243)', 'rgb(234, 234, 196', 'rgb(200, 221, 232)'];

function showAlert() {
    alert("The button was clicked!");
}

function sayIfCorrect() {
	var submitb, tagc, nextb;
	submitb = document.getElementById("submitb").style.visibility = "hidden";
	tagc = document.getElementById("tagc").style.visibility = "visible";
	nextb = document.getElementById("nextb").style.visibility = "visible";

}
	