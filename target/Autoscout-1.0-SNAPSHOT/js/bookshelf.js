/**
 * view-controller for bookshelf.html
 * @author Marcel Suter
 */
let delayTimer;
const userRole = getCookie("userRole");

document.addEventListener("DOMContentLoaded", () => {
    showNav(userRole);
    showHeadings();
    readBooks();

    document.getElementById("search").addEventListener("keyup", searchBooks);
});

/**
 * reads all books
 */
function readBooks() {
    let url = "./resource/book/list";
    let sorting = getSort();
    url += "?sortField=" + sorting[0];
    url += "&sort=" + sorting[1];

    let filter = getFilter();
    url += "&filterField=" + filter[0];
    url += "&filter=" + filter[1];

    fetch(url, {
        headers: {"Authorization": "Bearer " + readStorage("token")}
    })
        .then(function (response) {
            if (response.ok) {
                return response;
            } else if (response.status == 401) {
                window.location.href = "./";
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showBooklist(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * look up the search-fields and create the filter
 * @param event
 */
function searchBooks(event) {
    const searchFields = ["titleFilter", "authorFilter", "publisherFilter", "priceFilter", "isbnFilter"]
    const element = event.target;
    const field = element.id;
    let filter = "";

    searchFields.forEach(searchField => {
        let element = document.getElementById(searchField);
        if (searchField === field) {
            filter = element.value;
        } else {
            element.value = "";
        }

    });
    sessionStorage.setItem("filterField", field);
    sessionStorage.setItem("filterValue", filter);

    clearTimeout(delayTimer);
    delayTimer = setTimeout(() => {

        readBooks();
    }, 500);
}

/**
 * shows the booklist as a table
 * @param data  the books
 */
function showBooklist(data) {
    showHeadings();
    let tBody = document.getElementById("booklist");
    tBody.innerHTML = "";
    data.forEach(book => {
        let row = tBody.insertRow(-1);

        let button = document.createElement("button");
        if (userRole === "user" || userRole === "admin")
            button.innerHTML = "&#9998;";
        else
            button.innerHTML = "&#128065;";
        button.type = "button";
        button.name = "editBook";
        button.setAttribute("data-bookuuid", book.bookUUID);
        button.addEventListener("click", editBook);
        row.insertCell(-1).appendChild(button);

        row.insertCell(-1).innerHTML = book.title;
        row.insertCell(-1).innerHTML = book.authors;
        row.insertCell(-1).innerHTML = book.publisher.publisherName;
        row.insertCell(-1).innerHTML =
            book.price.toLocaleString("de-CH", {
                style: "currency",
                currency: "CHF",
                maximumFractionDigits: 2,
                minimumFractionDigits: 2
            });
        row.insertCell(-1).innerHTML = book.isbn;

        if (userRole === "admin") {
            button = document.createElement("button");
            button.innerHTML = "&#128465;";
            button.type = "button";
            button.name = "deleteBook";
            button.setAttribute("data-bookuuid", book.bookUUID);
            button.addEventListener("click", deleteBook);
            row.insertCell(-1).appendChild(button);
        }

    });

    if (userRole === "user" || userRole === "admin") {
        document.getElementById("addButton").innerHTML = "<a href='./bookedit.html'>Neues Buch</a>";
    }
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editBook(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");
    window.location.href = "./bookedit.html?uuid=" + bookUUID;
}

/**
 * deletes a book
 * @param event  the click-event
 */
function deleteBook(event) {
    const button = event.target;
    const bookUUID = button.getAttribute("data-bookuuid");

    fetch("./resource/book/delete?uuid=" + bookUUID,
        {
            method: "DELETE",
            headers: {"Authorization": "Bearer " + readStorage("token")}
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./bookshelf.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * gets the sort field and order
 */
function getSort() {
    let sortField = readStorage("sortField");
    let sortOrder = readStorage("sortOrder");
    if (!sortField || sortField.length === 0) {
        sortField = "title";
        sortOrder = "ASC";
    }
    return [sortField, sortOrder];
}

function getFilter() {
    let filterField = readStorage("filterField");
    let filterOrder = readStorage("filterValue");
    if (!filterField || filterField.length === 0) {
        filterField = "";
        filterOrder = "";
    }
    return [filterField, filterOrder];
}
function showHeadings() {
    const sort = getSort();
    const ids = ["title", "author", "publisher", "price", "isbn"];
    const labels = ["Titel", "Autor", "Verlag", "Preis", "ISBN"];

    let row = document.getElementById("headings");
    row.innerText = "";
    row.insertCell(-1);
    for (let i=0; i<labels.length; i++) {
        let cell = row.insertCell(-1);
        if (ids[i] !== sort[0]) {
            cell.innerHTML = labels[i];
        } else if (sort[1] === "ASC") {
            cell.innerHTML = "&uarr;&nbsp;" + labels[i];
        } else {
            cell.innerHTML = labels[i] + "&darr;&nbsp;";
        }
        cell.id=ids[i];
        cell.addEventListener("click", setSort);
    }
}

/**
 * sets the field and order for sorting
 * @param event
 */
function setSort(event) {
    let sort = getSort();
    let field = event.target.id;
    let order = "ASC";
    if (field === sort[0]) {
        if (sort[1] === "ASC") {
            order = "DESC";
        }
    }
    sessionStorage.setItem("sortField", field);
    sessionStorage.setItem("sortOrder", order);
    readBooks();
}