'use strict';
    //const studenList =  document.getElementById("estudiantes");
    const base = "http://localhost:8080/Entregable2/rest/";
    
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
        
        const response = await fetch(base + "estudiantes", {
            "method": 'GET',
            "mode": 'no-cors'
        });
        const data = await response.json();
        let lista_estudiantes = document.querySelector("#estudiantes");
        let lista = "";
        data.forEach(estudiante => {
            lista += 
            `<li class="list-group-item">
                <div class="name">
                    <h2><b>${estudiante.nombres} ${estudiante.apellidos}</b></h2>
                </div>
                <div class="name">
                    <h3>LU: ${estudiante.lu} Edad: ${estudiante.edad} DNI: ${estudiante.dni} Género: ${estudiante.genero}</h3>
                </div>
            </li>`;
        lista_estudiantes.innerHTML = lista;
        });
    }
    
    // D) DEVUELVE ESTUDIANTE POR LU
    const getEstudiante = () => {
        const lu = document.querySelector("#lu_d").value;
        const url = `${base}estudiantes/${lu}`;
        let estudiante = "";
        let responseAlumno = document.querySelector("#response-alumno");
        fetch(url)
            .then(response => response.json())
            .then(data => {
                estudiante += 
            `<li class="list-group-item">
                <div class="name">
                    <h2><b>${data.nombres} ${data.apellidos}</b></h2>
                </div>
                <div class="name">
                    <h3>LU: ${data.lu} Edad: ${data.edad} DNI: ${data.dni} Género: ${data.genero}</h3>
                </div>
                
            </li>`;
            responseAlumno.innerHTML = estudiante;
            })
            .catch(() => responseAlumno.innerHTML = `Alumno ${lu} no encontrado`);
    }
    
    // E) LISTAR LOS ESTUDIANTES SEGÚN UN GÉNERO
    const getEstudiantesPorGenero = () => {
        const genero = document.querySelector("#genero_e").value;
        const url = `${base}estudiantes/genero/${genero}`;
        let estudiantes = "";
        let responseAlumnos = document.querySelector("#response-alumno-genero");
        fetch(url)
            .then(response => response.json())
            .then(data => data.forEach(estudiante => {
                estudiantes += 
                `<li class="list-group-item">
                    <div class="name">
                        <h2><b>${estudiante.nombres} ${estudiante.apellidos}</b></h2>
                    </div>
                    <div class="name">
                        <h3>LU: ${estudiante.lu} Edad: ${estudiante.edad} DNI: ${estudiante.dni} Género: ${estudiante.genero}</h3>
                    </div>
                    
                </li>`;
                responseAlumnos.innerHTML = estudiantes;
            }))
            .catch(error => alert(error.message)
        );
    }

    // F) LISTAR LAS CARRERAS CON INSCRIPTOS
    const getCarrerasConInscriptos = () => {
        const url = `${base}carreras/inscriptos`;
        let carreras = "";
        let responseCarreras = document.querySelector("#response-carrera-con-alumnos");
        fetch(url)
            .then(response => response.json())
            .then(data => data.forEach(carrera => {
                carreras += 
                `<li class="list-group-item">
                    <div class="name">
                        <h2><b>${carrera.nombre}</b></h2>
                    </div>
                </li>`;
                responseCarreras.innerHTML = carreras;
            }))
            .catch(error => alert(error.message)
        );
    }
    
    // G) LISTAR LOS ESTUDIANTES DE UNA CARRERA, FILTRADO POR CIUDAD
    const getEstudiantesCarreraCiudad = () => {
        const carrera = document.querySelector("#idCarrera2").value;
        const ciudad = document.querySelector("#ciudad2").value;
        const url = `${base}estudiantes/${carrera}/${ciudad}`;
        let estudiantes = "";
        let responseEstudiantes = document.querySelector("#response-alumno-por-carrera-ciudad");
        fetch(url)
            .then(response => response.json())
            .then(data => data.forEach(estudiante => {
                estudiantes += 
                `<li class="list-group-item">
                    <div class="name">
                        <h2><b>${estudiante.nombres} ${estudiante.apellidos}</b></h2>
                    </div>
                    <div class="name">
                        <h3>LU: ${estudiante.lu} Ciudad: ${estudiante.ciudad}</h3>
                    </div>
                    
                </li>`;
                responseEstudiantes.innerHTML = estudiantes;
            })).catch(error => alert(error.message)
        );
    }

    // H) LISTAR CARRERAS CON INSCRIPTOS Y EGRESADOS POR AÑO
    const getCarrerasInscriptosYEgresados = () => {
        const url = `${base}carreras/reporteCarreras`;
        let carreras = "";
        let responseCarreras = document.querySelector("#response-reporte");
        fetch(url)
            .then(response => response.json())
            .then(data => data.forEach(carrera => {
                carreras += 
                `<li class="list-group-item">
                    <div class="name">
                        <h2><b>${carrera.carrera.nombre}</b></h2>
                    </div>
                    <div class="name">
                        <h3><b>${carrera.carrera.estudiantes[0]}</b></h3>
                    </div>
                </li>`;
                responseCarreras.innerHTML = carreras;
            }))
            .catch(error => alert(error.message)
        );
    }

    
    document.querySelector("#btn_registrar").addEventListener("click", registrar);
    document.querySelector("#btn_matricular").addEventListener("click", matricular);
    document.querySelector("#btn_estudiantes").addEventListener("click", getEstudiantes);
    document.querySelector("#btn_buscar_d").addEventListener("click", getEstudiante);
    document.querySelector("#btn_buscar_e").addEventListener("click", getEstudiantesPorGenero);
    document.querySelector("#btn_carreras").addEventListener("click", getCarrerasConInscriptos);
    document.querySelector("#btn_buscar_g").addEventListener("click", getEstudiantesCarreraCiudad);
    document.querySelector("#btn_reporte").addEventListener("click", getCarrerasInscriptosYEgresados);

    