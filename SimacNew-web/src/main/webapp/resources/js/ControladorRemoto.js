var tipo = 'M';
var dispositivo = 1874;
var timeout = 120;
var consultasActivas = new Array();
var arrayTimers = new Array();

function timer(id) {

    for (var i = 0; i < 2; i++)
    {
        if ((arrayTimers[i][0] === id) && (arrayTimers[i][1] === null || arrayTimers[i][2] === false))
        {
            console.log('Log ' + i + ' ' + arrayTimers[i][2]);
            var downloadTimer = setInterval(function () {
                arrayTimers[i][3]--;
                document.getElementById(id).textContent = arrayTimers[i][3];
                if (arrayTimers[i][3] <= 0)
                {
                    arrayTimers[i][3] = 10;
                    clearInterval(downloadTimer);
                    arrayTimers[i][2] = false;
                }
            }, 1000);
            arrayTimers[i][1] = downloadTimer;
            arrayTimers[i][2] = true;
            break;
        } else if ((arrayTimers[i][0] === id) && (arrayTimers[i][2] === true)) { //Si esta corriendo, entonces agrega segundos
            console.log('else!');
            arrayTimers[i][3] = 10;
        }
    }

}
function verRespuestas() {
    var t = 0;
    var interval = setInterval(function () {
        if (consultasActivas.length !== 0 && i !== timeout)
        {
            var consultas = '';
            for (var i = 0; i < consultasActivas.length; i++)
            {
                consultas += consultasActivas[i] + ',';
            }
            $(document).ready(function () {
                $.ajax({
                    type: "POST",
                    url: '../ConsultasTerminalServlet',
                    data: {consultas: consultas, operacion: 'R'},
                    success: function (response) {
                        var textarea = document.getElementById('respuestas');
                        var res = response.trim().split('\n');
                        if (res[0].toString().trim() === 'error')
                        {
                            document.getElementById('respuestas').value = document.getElementById('respuestas').value + 'Se ha producido un error de conexion y se ha cancelado la operacion.' + "\n\n";
                        } else if (res[0].toString().trim() !== '')
                        {
                            var respFinal = '';
                            var arrayAux = consultasActivas.slice();
                            for (var i = 0; i < arrayAux.length; i++)
                            {
                                for (var j = 0; j < res.length / 3; j++)
                                {
                                    if (res[j * 3].trim() === arrayAux[i].toString().trim())
                                    {
                                        consultasActivas.splice(i, 1);
                                    }
                                }

                            }

                            for (var i = 0; i < res.length / 3; i++)
                            {
                                respFinal += res[i + 1].trim() + "\n" + res[i + 2].trim();
                            }
                            document.getElementById('respuestas').value = document.getElementById('respuestas').value + respFinal + "\n\n";
                            textarea.scrollTop = textarea.scrollHeight;
                            /*var valor = (100) * (i + 1) / args.length;
                             move(valor);
                             if (i + 1 < args.length)
                             {
                             executeCMD(args, i + 1);
                             }*/
                        }
                    },
                    dataType: 'text'
                });
            });

        }
        if (t === timeout || consultasActivas.length === 0)
        {
            if (consultasActivas.length > 0)
            {
                for (var i = 0; i < consultasActivas.length; i++)
                {
                    document.getElementById('respuestas').value = document.getElementById('respuestas').value + '\n Timeout' + '\n';
                }
                consultasActivas = [];
            }
            document.getElementById("comandos").disabled = false;
            document.getElementById("btn1").disabled = false;
            clearInterval(interval);
        }
        t++;
    }, 1000);

}
function enviarComandos() {
    var comandos = document.getElementById("comandos").value;
    if (comandos.trim() !== "")
    {
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: '../ConsultasTerminalServlet',
                data: {comandos: comandos, operacion: 'E', dispositivo: dispositivo, tipo: tipo},
                success: function (response) {
                    var res = response.trim().split('\n');
                    if (res[0].toString().trim() === 'error')
                    {
                        document.getElementById('respuestas').value = document.getElementById('respuestas').value + 'Se ha producido un error de conexion y se ha cancelado la operacion.' + "\n\n";
                        desconectar();
                    } else
                    {
                        for (var j = 0; j < res.length; j++)
                        {
                            consultasActivas.push(res[j]);
                        }
                    }
                },
                dataType: 'text'
            });
        });
        verRespuestas();
        document.getElementById("comandos").disabled = true;
        document.getElementById("btn1").disabled = true;
    }
}
function limpiarVentanaComandos() {
    document.getElementById("comandos").value = "";

}
function limpiarVentanaRespuestas() {
    document.getElementById("respuestas").value = "";

}
function setTipo(modo) {
    tipo = modo;
    console.log('Tipo: ' + tipo);
}
function cambiafase(boton) {
    var variables = boton.id.trim().split('-');
    var comando = variables[1] + '-' + variables[2];
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: '../ConsultasTerminalServlet',
            data: {comandos: comando, operacion: 'E', dispositivo: dispositivo, tipo: 'M'},
            success: function (response) {
                var res = response.trim().split('\n');
                console.log('Respuesta: ' + response.trim());
            },
            dataType: 'text'
        });
    });
}
function llenarPPR() {
    arrayTimers[0] = new Array();
    arrayTimers[1] = new Array();

    arrayTimers[0][0] = 'countdowntimer';
    arrayTimers[1][0] = 'countdowntimer2';
    arrayTimers[0][2] = false;
    arrayTimers[1][2] = false;
    arrayTimers[0][3] = 10;
    arrayTimers[1][3] = 10;
}

function loadFileAsText()
{
    var elem = document.getElementById('file-input');
    if (elem && document.createEvent) {
        var evt = document.createEvent("MouseEvents");
        evt.initEvent("click", true, false);
        elem.dispatchEvent(evt);
    }
}
function saveTextAsFile()
{
    var textToWrite = document.getElementById('comandos').value;
    if (textToWrite !== '') {
        var textFileAsBlob = new Blob([textToWrite], {type: 'text/plain'});
        var fileNameToSaveAs = "comandos.txt";

        var downloadLink = document.createElement("a");
        downloadLink.download = fileNameToSaveAs;
        downloadLink.innerHTML = "Download File";
        if (window.webkitURL !== null)
        {
            downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
        } else
        {
            // Firefox requires the link to be added to the DOM
            // before it can be clicked.
            downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
            downloadLink.onclick = destroyClickedElement;
            downloadLink.style.display = "none";
            document.body.appendChild(downloadLink);
        }
        downloadLink.click();
    }
}
function displayContents(contents) {
    var element = document.getElementById('comandos');
    element.value = contents;
}
function readSingleFile(event) {
    var file = event.target.files[0];
    if (!file) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (event) {
        var contents = event.target.result;
        displayContents(contents);
    };
    reader.readAsText(file);
    document.getElementById('file-input').value = "";
}
function destroyClickedElement(event)
{
    document.body.removeChild(event.target);
}