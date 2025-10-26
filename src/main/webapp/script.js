window.onload = function() {
    fetch('tasks')
    .then(res => res.json())
    .then(data => {
        data.forEach(task => {
            addTaskToDOM(task);
        });
    });
};

function addTaskToDOM(task) {
    let div = document.createElement('div');
    div.className = 'task';
    div.innerHTML = `
        <form action="updateStatus" method="post">
            <input type="hidden" name="id" value="${task.id}">
            <select name="status" onchange="this.form.submit()">
                <option value="Not Started" ${task.status=="Not Started" ? "selected":""}>Not Started</option>
                <option value="Ongoing" ${task.status=="Ongoing" ? "selected":""}>Ongoing</option>
                <option value="Done" ${task.status=="Done" ? "selected":""}>Done</option>
            </select>
            <span>${task.title}</span>
        </form>`;
    
    if(task.status=="Not Started") document.getElementById('not-started').appendChild(div);
    else if(task.status=="Ongoing") document.getElementById('ongoing').appendChild(div);
    else document.getElementById('done').appendChild(div);
}
