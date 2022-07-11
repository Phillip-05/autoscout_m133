/**
 * view-controller for publisherlist.html
 * @author Marcel Suter
 */
let delayTimer;
const userRole = getCookie("userRole");

document.addEventListener("DOMContentLoaded", () => {
    readPublishers("","");
    showNav(userRole);
    document.getElementById("search").addEventListener("keyup", searchPublishers);
});

/**
 * reads all publishers
 * @param field   the attribute to be used as a filter or sort
 * @param filter  the value to be filtered by
 * @param sort    the sort order
 */
function readPublishers(field, filter, sort) {
    let url = "./resource/publisher/list";
    if (field !== "" && filter !== "") {
        url += "?field=" + field;
        url += "&filter=" + filter;
    }
    fetch(url, {
        headers: { "Authorization": "Bearer " + readStorage("token")}
    })
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showPublisherList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * look up the search-fields and create the filter
 * @param event
 */
function searchPublishers(event) {
    const element = event.target;
    const field = element.id;
    let filter = "";
    filter = document.getElementById("publisherName").value;

    clearTimeout(delayTimer);
    delayTimer = setTimeout(() => {
        readPublishers(field, filter);
    }, 500);
}
/**
 * shows the publishers as a table
 * @param data  the publishers
 */
function showPublisherList(data) {
    const userRole = getCookie("userRole");
    let tBody = document.getElementById("publisherlist");
    tBody.innerHTML = "";
    data.forEach(publisher => {
        let row = tBody.insertRow(-1);

        let button = document.createElement("button");
        if (userRole === "user" || userRole === "admin")
            button.innerHTML = "&#9998;";
        else
            button.innerHTML = "&#128065;";

        button.type = "button";
        button.name = "editBook";
        button.setAttribute("data-publisheruuid", publisher.publisherUUID);
        button.addEventListener("click", editPublisher);
        row.insertCell(-1).appendChild(button);

        row.insertCell(-1).innerHTML = publisher.publisherName;


        if (userRole === "admin") {
            button = document.createElement("button");
            button.innerHTML = "&#128465;";
            button.type = "button";
            button.name = "deleteBook";
            button.setAttribute("data-publisheruuid", publisher.publisherUUID);
            button.addEventListener("click", deletePublisher);
            row.insertCell(-1).appendChild(button);
        }

    });

    if (userRole === "user" || userRole === "admin") {
        document.getElementById("addButton").innerHTML = "<a href='./publisheredit.html'>Neues Buch</a>";
    }
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editPublisher(event) {
    const button = event.target;
    const publisherUUID = button.getAttribute("data-publisheruuid");
    window.location.href = "./publisheredit.html?uuid=" + publisherUUID;
}

/**
 * deletes a publisher
 * @param event  the click-event
 */
function deletePublisher(event) {
    const button = event.target;
    const publisherUUID = button.getAttribute("data-publisheruuid");

    fetch("./resource/publisher/delete?uuid=" + publisherUUID,
        {
            method: "DELETE",
            headers: { "Authorization": "Bearer " + readStorage("token")}
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./publisherlist.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}