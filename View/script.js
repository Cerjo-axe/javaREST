const url = "http://localhost:8080/task/user/1"

function hideLoader() {
    document.getElementById("loading").style.display = "none";
}

function show(tasks) {
    let tab = `<thead>
                <th scope="col">#</th>
                <th scope="col">Description</th>
                <th scope="col">Username</th>
                <th scope="col">User</th>
            </thead>`

    for (let task of tasks) {
        tab += `
            <tr>
                <td>${task.id}</td>
                <td>${task.description}</td>
            </tr>
        `
    }

    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(link) {
    const response = await fetch(link, { method: "GET" });
    var data = await response.json();
    console.log(data);
    if (response) {
        hideLoader();
        show(data);
    }
}
getAPI(url);