/**
 * view-controller for bookedit.html
 * @author Marcel Suter
 */
const userRole = getCookie("userRole");
document.addEventListener("DOMContentLoaded", () => {
    readPublisher();
    showNav(userRole);

    document.getElementById("publishereditForm").addEventListener("submit", savePublisher);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a publisher
 */
function savePublisher(event) {
    event.preventDefault();
    showMessage("", "info");

    const publisherForm = document.getElementById("publishereditForm");
    const formData = new FormData(publisherForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/publisher/";
    const publisherUUID = getQueryParam("uuid");
    if (publisherUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": "Bearer " + readStorage("token")
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                showMessage("Fehler beim Speichern", "error");
                console.log(response);
            } else {
                showMessage("Buch gespeichert", "info");
            return response;}
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * reads a publisher
 */
function readPublisher() {
    const publisherUUID = getQueryParam("uuid");
    fetch("./resource/publisher/read?uuid=" + publisherUUID, {
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
            showPublisher(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a publisher
 * @param data  the publisher-data
 */
function showPublisher(data) {
    const userRole = getCookie("userRole");
    document.getElementById("publisherUUID").value = data.publisherUUID;
    document.getElementById("publisherName").value = data.publisherName;

    const locked =  !(userRole === "user" || userRole === "admin");
    lockForm("publishereditForm", locked);
}

/**
 * redirects to the publisherlist
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./publisherlist.html";
}