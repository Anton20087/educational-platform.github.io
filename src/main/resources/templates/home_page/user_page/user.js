function redirectToPage() {
    window.location.href = "../../login/index.html";
}

(() => {
    document.querySelector("button#logout").onclick = _ => {
        const jwt = JSON.parse(document.querySelector("pre#csrf").innerText);
        fetch(`/logout?${jwt.parameterName}=${jwt.token}`, {
            method: "POST"
        }).then(_ => {
            redirectToPage()
        })
    }
})();