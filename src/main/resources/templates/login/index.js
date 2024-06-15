document.getElementById("forgotPassword").addEventListener("click", function(event){
    event.preventDefault();
    document.getElementById("loginContainer").style.display = "none";
    document.getElementById("resetPasswordContainer").style.display = "block";
});

document.getElementById("backLink").addEventListener("click", function(event){
    event.preventDefault();
    document.getElementById("loginContainer").style.display = "block";
    document.getElementById("resetPasswordContainer").style.display = "none";
    document.getElementById("resetPasswordEmailContainer").style.display = "none";
    document.getElementById("resetPasswordLoginContainer").style.display = "none";
});

document.getElementById("loginResetLink").addEventListener("click", function(event){
    event.preventDefault();
    document.getElementById("resetPasswordContainer").style.display = "none";
    document.getElementById("resetPasswordLoginContainer").style.display = "block";
});

document.getElementById("emailResetLink").addEventListener("click", function(event){
    event.preventDefault();
    document.getElementById("resetPasswordContainer").style.display = "none";
    document.getElementById("resetPasswordEmailContainer").style.display = "block";
});

function redirectToPage() {
    window.location.href = "../home_page_guest/home_page_guest.html";
}