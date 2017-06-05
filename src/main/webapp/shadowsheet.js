/**
 * Created by DaMasterHam on 05-06-2017.
 */

// Gets the personal info of runner 1
$.getJSON("http://localhost:8080/api/runners/1/personalinfo", function (data)
{
    var personalInfo = [];

    // Gest each key value pair from the JSON file
    $.each(data, function(key, val)
    {
        // Formats the data in html
        personalInfo.push('<p><b>' + key + ':</b> ' + val + '</p>');
    });

    // Appends the formatted data to the page
    var runner = $('.runner-box');
    runner.append("<h1>Runner</h1>");
    runner.append(personalInfo.join(""));
});


// $.getJson is shorthand for
//
// $.ajax({
//     dataType: "json",
//     url: url,
//     data: data,
//     success: success
// });


// wich is shorthand for
// var xhttp = new XMLHttpRequest();
// xhttp.onreadystatechange = function() {
//     if (this.readyState == 4 && this.status == 200) {
//         document.getElementById("demo").innerHTML =
//             this.responseText;
//     }
// };
// xhttp.open("GET", "ajax_info.txt", true);
// xhttp.send();
