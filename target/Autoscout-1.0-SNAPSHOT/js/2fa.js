/**
 * view-controller for 2fa-form
 */
const userRole = getCookie("userRole");
document.addEventListener("DOMContentLoaded", () => {
    showNav(userRole);
    showSecret();
    document.getElementById("2faForm").addEventListener("submit", twoFAUser);
});

function twoFAUser(event) {
    event.preventDefault();
    showMessage("", "info");

    fetch("./resource/user/2fa",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "secret=" + document.getElementById("secret").value
        })
        .then(function (response) {
            if (!response.ok) {
                showMessage("Falsches Geheimwort", "error");
            } else loginSuccess(response)
        })
        .catch(function (error) {
            console.log(error);
        });
}


/**
 * saves the JWToken and redirects
 * @param response
 */
function loginSuccess(response) {
    saveToken(response.headers);
    window.location.href = "./bookshelf.html";
}

/**
 * shows the number of the secret word to enter
 */
function showSecret() {
    const index = getCookie("secret");
    if (index != null) {
        document.getElementById("index").innerText = index;
    } else {
        //window.location.href = "./index.html";
    }
}