document.addEventListener("DOMContentLoaded", ()=> {
    //const studenList =  document.getElementById("estudiantes");
    const base = "http://localhost:8080/Entregable2/rest/";
    
    document.querySelector("#btn_registrar").addEventListener("click", registrar);
    document.querySelector("#btn_matricular").addEventListener("click", matricular);
    document.querySelector("#btn_estudiantes").addEventListener("click", getEstudiantes);
    document.querySelector("#btn_buscar_d").addEventListener("click", getEstudiante);
    document.querySelector("#btn_buscar_e").addEventListener("click", getEstudiantesPorGenero);
    document.querySelector("#btn_carreras").addEventListener("click", getCarrerasConInscriptos);
    document.querySelector("#btn_reporte").addEventListener("click", getCarrerasInscriptosYEgresados);
    
    // A) ALTA DE ESTUDIANTE
    function registrar(){
        let lu = document.querySelector("#lu_a").value;
        let nombres = document.querySelector("#nombres").value;
        let apellidos = document.querySelector("#apellidos").value;
        let genero = document.querySelector("#genero_a").value;
        let edad = document.querySelector("#edad").value;
        let dni = document.querySelector("#dni").value;
        let ciudad = document.querySelector("#ciudad").value;
        let estudiante = {
            lu: lu,
            apellidos: apellidos,
            ciudad: ciudad,
            dni: dni,
            edad: edad,
            genero: genero, 
            nombres: nombres
        };
        fetch(base + "estudiantes", {
            "method": 'POST',
            "mode": 'cors',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(estudiante)
        })
        alert("Registro de estudiante exitoso!");
    }

    // B) MATRICULAR UN ESTUDIANTE EN UNA CARRERA
    function matricular(){
        let lu = document.querySelector("#lu_b").value;
        let idCarrera = document.querySelector("#idCarrera").value;
        console.log(lu);
        console.log(idCarrera);    
        
        fetch(base + "inscripcion" + "/" + lu + "/" + idCarrera, {
            "method": 'POST',
            "mode": 'cors',
            "headers": { "Content-Type": "application/json" }
        })
        alert("Inscripción exitosa!");
    } 

    // C) LISTAR ESTUDIANTES
    async function getEstudiantes() {
        
        const response = await fetch(base + "estudiantes");
        const data = await response.json();
        let lista_estudiantes = document.querySelector("#estudiantes");
        let lista = "";
        data.forEach(estudiante => {
            lista += 
            `<li class="student">
                <div class="name">
                    <h2><b>${estudiante.nombres} ${estudiante.apellidos}</b></h2>
                </div>
                <div class="name">
                    <p>LU: ${estudiante.lu} Edad: ${estudiante.edad} DNI: ${estudiante.dni} Género: ${estudiante.genero}</p>
                </div>
                
            </li>`;
        lista_estudiantes.innerHTML = lista;
        });
       return data;
    }

    // D) DEVUELVE ESTUDIANTE POR LU
    async function getEstudiante() {
        let lu = document.querySelector("#lu_d").value;
        console.log(lu);
        const response = await fetch(base + "estudiantes/" + lu);
        const data = await response.json();
        console.log(data.JSON.apellidos);
        console.log(data.JSON.lu);
        alert("..entro..");
        return data;
    }
    
    // E) LISTAR LOS ESTUDIANTES SEGÚN UN GÉNERO
    async function getEstudiantesPorGenero() {
        let genero = document.querySelector("#genero_e").value;
        const response = await fetch(base + "estudiantes" + "/genero/" + genero);
        const data = await response.json();
        console.log(data.JSON.apellidos);
        console.log(data.JSON.genero);
        alert("..entro..");
        return data;
    }

    
    // f) LISTAR CARRERAS CON INSCRIPTOS
    async function getCarrerasConInscriptos() {
        const response = await fetch(base + "carreras" + "/inscriptos");
        const data = await response.json();
        data.forEach(carrera => {
            console.log(carrera.nombre);
            alert("...");
        });
        return data;
    }

    // G) LISTAR CARRERAS CON INSCRIPTOS Y EGRESADOS POR AÑO
    async function getCarrerasInscriptosYEgresados() {
        const response = await fetch(base + "carreras" + "/reporteCarreras");
        const data = await response.json();
        data.forEach(carrera => {
            console.log(carrera.nombre);
            alert("....");
        });
        return data;
    }

    /*
    let register_form = document.getElementById('register_form');
    register_form.onsubmit = function(e) {
        e.preventDefault();
        let formData = new FormData(this);
        let jsonData = {};
        for (let [k, v] of formData) {
            jsonData[k] = v;
        }
        console.log(JSON.stringify(jsonData));
        fetch(base +"estudiante", 
            {
                method: 'POST', 
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify(jsonData),
            })
          .then(response => response.json())
          .then(json => console.log(json))
    }*/

    /*
    function loadList(estudiantes, carreras) {
        let list = "";
        estudiantes.forEach(e => { 
            list += `
            <li class="student">
                <div class="name">
                    ${e.nombre} ${e.apellido}
                </div>
                <div class="form">
                    <form data-id-estudiante="${e.num_libreta}">
                        <label>Carrera
                            <select>`;
                            carreras.forEach(c => {
                                list += `<option data-id-carrera="${c.id}">
                                    ${c.nombre}
                                </option>`;
                            });
                            list += `</select>
                        </label>
                        <button class="mat_button" type="submit">Matricular</button>
                    </form>
                </div>
            </li>`;
            studenList.innerHTML = list;
        });
        addEvents()
    }
    

    function addEvents() {
        document.getElementById("estudiantes").querySelectorAll("form").forEach( form => {
            form.addEventListener("submit", (e)=> {
                e.preventDefault();
                let idCareer = form.querySelector("select").selectedOptions[0].getAttribute("data-id-carrera");  
                let idStudent = form.getAttribute("data-id-estudiante");  
                fetch(base +"carrera/"+idCareer +"/matricular-estudiante/"+idStudent,{method: "POST"});
            });
        });
    }

    async function foo() {
        try {
            let careers = await getCareer();
            let users = await getUsers();
            loadList(users, careers);
        } catch(error) { }
    }
    foo()
    

    const buscarAlmunoDiv = document.getElementById("response-alumno")
    const formBuscarAlumno = document.getElementById("for-buscar-alumno");
    formBuscarAlumno.addEventListener("submit", (e) => {
        e.preventDefault();
        fetch(base+"estudiante/"+formBuscarAlumno.querySelector("input").value).
        then( text => text.json()).
        then( e =>  
                buscarAlmunoDiv.innerHTML = 
                `<p>Nombre: ${e.nombre} ${e.apellido}</p>  
                <p>DNI: ${e.num_doc}</p>
                <p>Número de libreta: ${e.num_libreta}</p>
                <p>Edad: ${e.edad}</p>
                <p>Ciudad de residencia: ${e.ciudad_residencia}</p>
                <p>Genero: ${e.genero}</p>`
            );
    });


    const buscarAlmunoGeneroDiv = document.getElementById("response-alumno-genero")
    const formBuscarAlumnoGenero = document.getElementById("for-buscar-alumno-genero");
    formBuscarAlumnoGenero.addEventListener("submit", (e) => {
        e.preventDefault();
        fetch(base+"estudiante/genero/"+formBuscarAlumnoGenero.querySelector("input").value).
        then( text => text.json()).
        then( estudiantes =>  {
                let estudiantess = "" 
                estudiantes.forEach(e => {
                    estudiantess += 
                    `<li class="student">
                        ${e.nombre} ${e.apellido} - ${e.genero} 
                    </li>`;
                });
                buscarAlmunoGeneroDiv.innerHTML = estudiantess;
            });
    });


    

        fetch(base+"carrera/con-inscriptos").
        then( text => text.json()).
        then( carreras =>  {
            let carrerass = "" 
            carreras.forEach(c => {
            carrerass += 
                `<div class="form">
                    ${c.nombre}
                </div>`;
            });
                document.getElementById("response-carrera-con-alumnos").innerHTML = carrerass;
            });
    

    fetch(base+"carrera/con-inscriptos").
        then( text => text.json()).
        then( carreras =>  {
                let carrerass = "" 
                    carrerass += 
                    `<div class="form">
                        <form>
                            <label>Carrera
                                <select>`;
                                carreras.forEach(c => {
                                    carrerass += `<option data-id-carrera="${c.id}">
                                        ${c.nombre}
                                    </option>`;
                                });
                                carrerass += `</select>
                            </label> <br>
                            <label>Ciudad</label>
                            <input class="input" type="text"  autocomplete="ciudad" required>
                            <button class="mat_button" type="submit">Buscar</button>
                        </form>
                    </div>`;
                document.getElementById("form-alumno-por-carrera-ciudad").innerHTML = carrerass;
                document.getElementById("form-alumno-por-carrera-ciudad").querySelector("form").addEventListener("submit", (e) => {
                    e.preventDefault();
                    let idCareer = e.target.querySelector("select").selectedOptions[0].getAttribute("data-id-carrera");  
                    let city = e.target.querySelector("input").value;  
                    fetch(base+"carrera/"+idCareer+"/estudiante/ciudad/"+city).
                    then( text => text.json()).
                    then( estudiantes =>  {
                            let estudiantess = "" 
                            estudiantes.forEach(e => {
                                estudiantess += 
                                `<li class="student">
                                    ${e.nombre} ${e.apellido}
                                </li>`;
                            });
                            document.getElementById("response-alumno-por-carrera-ciudad").innerHTML = estudiantess;
                        })
                });
            });
    


    fetch(base+"carrera/reporte").
    then( text => text.json()).
    then( reportes =>  {
        let text = "" 
        reportes.forEach(reporte => {
        text += 
        `<h3>Carrera: ${reporte.carrera.nombre}</h3>
        <p>Inscriptos por carrera</p>
        <ul>`;
            Object.keys(reporte.inscriptos).forEach( key => {
                reporte.inscriptos[key].forEach( e => {
                    text += 
                    `<li>
                        ${e.nombre} ${e.apellido} - ${key}
                    </li>`;
                })
            });
        text += `</ul>
        <p>Egresados  por carrera</p>
        <ul>`;
            Object.keys(reporte.egresados).forEach( key => {
                reporte.egresados[key].forEach( e => {
                    text += 
                    `<li>
                        ${e.nombre} ${e.apellido} - ${key}
                    </li>`;
                })
            })
        text += `</ul> <hr>`;
        });
            document.getElementById("response-reporte").innerHTML = text;
        });*/
});