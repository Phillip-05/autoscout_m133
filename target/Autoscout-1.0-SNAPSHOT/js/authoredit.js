/**
 * view-controller for bookedit.html
 * @author Marcel Suter
 */
const userRole = getCookie("userRole");
document.addEventListener("DOMContentLoaded", () => {
    showNav(userRole);
    readAuthor();

    document.getElementById("authoreditForm").addEventListener("submit", saveAuthor);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a author
 */
function saveAuthor(event) {
    event.preventDefault();
    showMessage("", "info");

    const authorForm = document.getElementById("authoreditForm");
    const formData = new FormData(authorForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/author/";
    const authorUUID = getQueryParam("uuid");
    if (authorUUID == null) {
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
 * reads a author
 */
function readAuthor() {
    const authorUUID = getQueryParam("uuid");
    fetch("./resource/author/read?uuid=" + authorUUID, {
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
            showAuthor(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a author
 * @param data  the author-data
 */
function showAuthor(data) {
    const userRole = getCookie("userRole");
    document.getElementById("authorUUID").value = data.authorUUID;
    document.getElementById("authorName").value = data.authorName;

    const locked =  !(userRole === "user" || userRole === "admin");
    lockForm("authoreditForm", locked);
}

/**
 * redirects to the authorlist
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./authorlist.html";
}