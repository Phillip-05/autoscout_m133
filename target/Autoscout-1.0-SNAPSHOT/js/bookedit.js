/**
 * view-controller for bookedit.html
 * @author Marcel Suter
 */
const userRole = getCookie("userRole");

document.addEventListener("DOMContentLoaded", () => {
    showNav(userRole);
    readAuthors();
    readPublishers();
    readBook();

    document.getElementById("bookeditForm").addEventListener("submit", saveBook);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * saves the data of a book
 */
function saveBook(event) {
    event.preventDefault();
    showMessage("", "info");

    const bookForm = document.getElementById("bookeditForm");
    const formData = new FormData(bookForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/book/";
    const bookUUID = getQueryParam("uuid");
    if (bookUUID == null) {
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
 * reads a book
 */
function readBook() {
    const bookUUID = getQueryParam("uuid");
    fetch("./resource/book/read?uuid=" + bookUUID, {
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
            showBook(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of a book
 * @param data  the book-data
 */
function showBook(data) {
    document.getElementById("bookUUID").value = data.bookUUID;
    document.getElementById("title").value = data.title;
    document.getElementById("publisher").value = data.publisherUUID;
    document.getElementById("price").value = data.price;
    document.getElementById("isbn").value = data.isbn;

    selectedAuthors(data.authorList);
    const locked =  !(userRole === "user" || userRole === "admin");
    lockForm("bookeditForm", locked);
}

/**
 * reads all publishers as an array
 */
function readPublishers() {

    fetch("./resource/publisher/list", {
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
            showPublishers(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all publishers as a dropdown
 * @param data
 */
function showPublishers(data) {
    let dropdown = document.getElementById("publisher");
    data.forEach(publisher => {
        let option = document.createElement("option");
        option.text = publisher.publisherName;
        option.value = publisher.publisherUUID;
        dropdown.add(option);
    })
}

/**
 * reads all authors as an array
 */
function readAuthors() {

    fetch("./resource/author/list", {
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
            showAuthors(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows all publishers as a dropdown
 * @param data
 */
function showAuthors(data) {
    let dropdown = document.getElementById("author");
    data.forEach(author => {
        let option = document.createElement("option");
        option.id = author.authorUUID;
        option.text = author.authorName;
        option.value = author.authorUUID;
        dropdown.add(option);
    })
}

function selectedAuthors(authorList) {
    authorList.forEach(author => {
        document.getElementById(author.authorUUID).selected = true;
    })
}
/**
 * redirects to the bookshelf
 * @param event  the click-event
 */
function cancelEdit(event) {
    window.location.href = "./bookshelf.html";
}