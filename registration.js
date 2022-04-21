/**************************************
 TITLE: registration.js
 AUTHOR: Christian Heatherly (CH)
 CREATE DATE: 04/18/2022
 PURPOSE: Allow users to register to vote on our system
 LAST MODIFIED ON: 04/20/2022
 LAST MODIFIED BY: Christian Heatherly (CH)	
 MODIFICATION HISTORY:
    04/18/2022: Creation date (CH)
    04/19/2022: Added every functionality except final registering. Needs to be converted to be used in the database (CH)
    04/20/2022: Edited functions & attempting to migrate to SQL (CH)
 SOURCES:
    https://stackoverflow.com/a/16991355
    https://www.w3schools.com/js/js_ajax_http.asp
    https://www.w3schools.com/js/js_json_stringify.asp
    https://stackoverflow.com/a/11197949
    https://www.w3schools.com/html/html_form_input_types.asp
    https://www.w3schools.com/js/js_json_parse.asp
    https://www.w3schools.com/js/js_json_html.asp
    https://www.w3schools.com/jsref/jsref_replace.asp
    https://stackoverflow.com/a/38363579 (Helped with JSON format, not used in final piece but still including)

***************************************/

$(document).ready(function() {

    var recordFile = "records.json";

    let publicKey = "csci43200voting";

    $("#finishedDiv").hide();

    function parse (file) { //can get rid of this entirely if you've implemented the database, this only has use when using JSON (can't write to the JSON file so we can't end up using this)

        /*
            Sources for this function:
                - https://stackoverflow.com/a/16991355
                - https://www.w3schools.com/js/js_ajax_http.asp
        */
        
        var loadJSONRequest = new XMLHttpRequest();
        loadJSONRequest.open("GET", file, false);
        loadJSONRequest.send();

        return JSON.parse(loadJSONRequest.responseText);
    }

    function validate (name, pass) {

        var searchInput = search(name, pass);

        if (searchInput == true) { //if user is found in records
            return false; //user exists, invalid registration
        } else if (searchInput == false) {
            return true; //user doesn't exist, valid registration
        }
    }

    function search (username) {
        //Helped with understanding how to properly loop through multiple JSON objects
        //  - https://stackoverflow.com/a/11197949
        //  - https://www.w3schools.com/js/js_json_html.asp

        //query database for username & password
        //return true if username & queried name match, false if not

        //Can remove this code down if you finish the database
        const parsedJSON = parse(recordFile);

        for (let x in parsedJSON) {
            var user = parsedJSON[x].name;

            if (user == username) {
                return true;
            }
        }

        return false;
    }

    function registerUser (username, pass, DOB) {

        //Helped with replacing strings, had some issues with syntax
        //  - https://www.w3schools.com/jsref/jsref_replace.asp
        
        //assuming validation already occurred, create new entry in database


        //Can remove this code down to the end of this function if you finish Database

        var parsedJSON = parse(recordFile);

        var append = {
            "name":username,
            "pass":pass,
            "dob":DOB
        };

        var strParsedJSON = JSON.stringify(parsedJSON);

        strParsedJSON = strParsedJSON.replace("]", ",") + JSON.stringify(append) + "]";

        alert ("Successful registration!");

        $("#registerDiv").hide();
        $("#finishedDiv").show();

    }

    $("#submitBtn").on("click", function() {

        var name = $("#voterName").val(); //retrieve user input for name
        var pass = $("#voterPass").val(); //retrieve user input for passphrase
        var dob = $("#voterDOB").val(); //retrieve user input for date of birth

        var val = validate(name, pass);

        if (val == true) {
            //register user
            registerUser (name, pass, dob);

        } else if (val == false) {
            alert ("Please try again! That user already exists!");
        }

        //clear form (need to fix)

        name = "";
        pass = "";
        dob = "";

        return false;
    });

});
