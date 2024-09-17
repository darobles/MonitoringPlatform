/*
 * Autor	: Rodrigo Baeza O
 * Fecha 	: 10/10/2013
 * Descripcion
 * -----------
 * Funcion que valida formato de numero con o sin decimales
 * 
 * Paramentros
 * ------------
 * id 		: Caja de texto asociada a la validacion
 * tecla 	: Objeto event de la caja de texto
 * entero 	: Es la cantidad que conforma la parte entera del numero
 * decimal  : Es la cantidad que conforma la parte decimal del numero
 * 
 * ejemplo de uso 
 * onkeyress="return validarNumero(this,event,5,0);" --- Solo permite un numero entero de 5 cifras
 * onkeyress="return validarNumero(this,event,3,2);" --- Solo permite un numero entre 0,0 a 999,99
 *  
 */
function validarNumero(id,tecla,entero, decimal){
	var largo=(id.value).length;
	var posicionComa=id.value.indexOf(',');
	
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	
	if (valorTecla == 8 || valorTecla == 0){
		return true;
	}
	if ((largo>0 && largo <=entero) && decimal>0){
		if (posicionComa ==-1 && largo==entero && valorTecla !=44){
			return false;
		}
		if (posicionComa !=-1 && valorTecla == 44){
			return false;
		}
		if (valorTecla == 44){
			return true;
		}
	}
	if (posicionComa !=-1){
		largo=largo+(entero-posicionComa);
	}
	
	if (largo >= entero+decimal){
		return false;
	}
	if (valorTecla<48 || valorTecla>57){
		return false;
	}
}
/*
 * Autor	: Rodrigo Baeza O
 * Fecha 	: 10/10/2013
 * Descripcion
 * -----------
 * Validacion de enter para numeros con o sin decimales
 * 
 * Paramentros
 * ------------
 * id 		: Caja de texto asociada a la validacion
 * tecla 	: Objeto event de la caja de texto
 * formato	: Valor que debe poner cuando el enter o tab son precionados y el texto esta en blanco
 * 
 * ejemplo de uso
 * onkeydown="eventoSalidaNumero(this,event,'0,00');" 
 *  
 */
function eventoSalidaNumero(id,tecla,formato,siguiente,anterior){
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	if (valorTecla ==38 ){
		if (anterior !=''){
		//	id.disabled=true;
		//	document.getElementById(anterior).disabled=false;
			document.getElementById(anterior).focus();
		}
	}
	if (valorTecla == 9){
		tecla.preventDefault ? tecla.preventDefault() : tecla.returnValue = false;
		valorTecla=0;
	}
	if ((valorTecla == 9 || valorTecla == 13) && id.value == '' ){
		id.value=formato;
	}
	if (valorTecla == 9 || valorTecla == 13){
		var tieneComa=formato.indexOf(',');
		if (tieneComa !=-1){
			var posicionComa=id.value.indexOf(',');
			if (posicionComa == -1){
				id.value=id.value+formato.substring(tieneComa);
			}else{
				var largoDespuesComa=id.value.substring(posicionComa+1).length;
				var cerosDespuesComa=formato.substring(tieneComa+1).length;
				if (largoDespuesComa<cerosDespuesComa){
					var repeticion=cerosDespuesComa-largoDespuesComa;
					id.value=id.value+(new Array(repeticion + 1).join('0'));
				}
			}
		}

		if (siguiente !=''){
			//	id.disabled=true;
			//	document.getElementById(siguiente).disabled=false;
			document.getElementById(siguiente).focus();
			
		}
	}
}

function validarLetras(id,tecla){
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	if (/^[0-9]+$/.test(String.fromCharCode(valorTecla))){
		return false;
	} 
}
function eventoSalidaLetra(id,tecla,siguiente,anterior){
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	if (valorTecla ==38 ){
		if (anterior !=''){
		//	id.disabled=true;
		//	document.getElementById(anterior).disabled=false;
			document.getElementById(anterior).focus();
		}
	}

	if (valorTecla == 9){
		tecla.preventDefault ? tecla.preventDefault() : tecla.returnValue = false;
		valorTecla=0;
	}
	if ( valorTecla == 13){
		if (siguiente !=''){
		//	id.disabled=true;
		//	document.getElementById(siguiente).disabled=false;
			document.getElementById(siguiente).focus();
		}
	}
}
function validarFecha(id,tecla){
	var dias = ["31","29","31","30","31","30","31","31","30","31","30","31"];
	var largo=(id.value).length;
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	
	if (valorTecla == 8 || valorTecla == 0){
		return true;
	}
	if (largo == 0 && !(/^[0-3]+$/.test(String.fromCharCode(valorTecla)))){
		return false;
	}
	if (largo ==1){
		if (id.value=='0' && !(/^[1-9]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		if ((id.value=='1' || id.value=='2') && !(/^[0-9]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		if (id.value=='3'  && !(/^[0-1]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
	}
	
	if (largo == 2 || largo == 3  ){
		if (!(/^[0-1]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		if (largo==2){
			id.value=id.value+'/';
		}
	}
	if (largo == 4){
		if ((id.value).substring(3) =='0' && !(/^[1-9]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		if ((id.value).substring(3) =='1' && !(/^[0-2]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		var mes=id.value.substring(3)+String.fromCharCode(valorTecla);
		if ((id.value).substring(0,2)>dias[mes-1]){
			return false;
		}
		
	}
	if (largo>4){
		if (!(/^[0-9]+$/.test(String.fromCharCode(valorTecla)))){
			return false;
		}
		if (largo==5){
			id.value=id.value+'/';
		}
	}
}

function eventoSalidaFecha(id,tecla,siguiente,anterior){
	
	var f = new Date();
	var dia=f.getDate();
	var mes=f.getMonth()+1;
	var ano=f.getFullYear();
	if (dia<10){
		dia='0'+dia;
	}
	if (mes<10){
		mes='0'+mes;
	}
	var fechaActual=dia+ "/" +mes+ "/" + ano;
	
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	if (valorTecla ==38 ){
		if (anterior !=''){
			id.disabled=true;
			document.getElementById(anterior).disabled=false;
			document.getElementById(anterior).focus();
		}
	}

	if (valorTecla == 9){
		tecla.preventDefault ? tecla.preventDefault() : tecla.returnValue = false;
		valorTecla=0;
	}
	if ( valorTecla == 13){
		if (id.value == '' ){
			id.value=fechaActual;
		}
		if ((id.value).length == 5 || (id.value).length == 6){
			if ((id.value).length == 5){
				id.value=id.value+'/'+ano;
			}
			if ((id.value).length == 6){
				id.value=id.value+ano;
			}
		}
		if ((id.value).length == 2 || (id.value).length == 3){
			if ((id.value).length == 2){
				id.value=id.value+'/'+mes+'/'+ano;
			}
			if ((id.value).length == 3){
				id.value=id.value+mes+'/'+ano;
			}
		}
		if ((id.value).length == 8){
				var diaMes=id.value.substring(0,6);
				var anoDigitado=id.value.substring(6);
				if (parseInt(anoDigitado)<50){
					id.value=diaMes+'20'+anoDigitado;
				}else{
					id.value=diaMes+'19'+anoDigitado;
				}
		}
		
		if (siguiente !='' && (id.value).length == 10){
	//		id.disabled=true;
		//	document.getElementById(siguiente).disabled=false;
			document.getElementById(siguiente).focus();
		}
	}
}


/*
 * Autor	: Rodrigo Baeza O
 * Fecha 	: 15/10/2013
 * Descripcion
 * -----------
 * Validacion de enter para cambiar de foco y eliminar el tab
 * 
 * Paramentros
 * ------------
 * id 		: Caja de texto asociada a la validacion
 * tecla 	: Objeto event de la caja de texto
 * formato	: Valor que debe poner cuando el enter o tab son precionados y el texto esta en blanco
 * 
 * ejemplo de uso
 * onkeydown="eventoSalidaNumero(this,event,'0,00');" 
 *  
 */

function enter(id,tecla,siguiente){
		if (tecla.keyCode == 9){
			tecla.preventDefault ? tecla.preventDefault() : tecla.returnValue = false;
		}
		if (tecla.keyCode == 8){
			if (id.name == 'idFormIngreso:idTextCuenta'){	
				document.getElementById("idFormIngreso:idTextDescripcion").value="";
			}
		}
		if (tecla.keyCode == 13){
				document.getElementById(siguiente).focus();
		}
	}


/*
 * Validacion del Email 
 * por caracter "@" y ".xx"
 * 
 * 
 * 
 */

function validarEmail(id,tecla) {
	var email = id.value;
	if (/^[A-Za-z][A-Za-z0-9_.]*@[A-Za-z0-9_]+\.[A-Za-z0-9_.]+[A-Za-z]$/.test(email)){
		return true;
	}else{	
		
		return false;  
	}  
}
/*
 * 
 * 
 * Validacion de null
 * 
 * 
 * 
 */
function validateNull(valor) {
	if (valor.value =="" || valor.value==null) {
		alert('Valor Invalido');
		value.focus;
		return false;
	}
}




/*
 * Validacion de Rut
 * 
 * 
 */

function validarRut(id,tecla){
	var valorTecla = (document.all) ? tecla.keyCode : tecla.which;
	var largo=(id.value).length;
	if (largo == 0 && !(/^[1-9]+$/.test(String.fromCharCode(valorTecla)))){
		return false;
	}
	if (id.value.indexOf('K') != -1 || id.value.indexOf('k') != -1)  {
		return false;
	}
	if (!(/^[0-9Kk]+$/.test(String.fromCharCode(valorTecla))))  {
		return false;
	}
}

function formatoRut(id){
	var rut=new String(id.value);
	var rutFormateado='';
	var dv='';
	sRut=quitarFormatoRut(rut);
	var largo=sRut.length;
	if (largo>1){
		dv=sRut.charAt(sRut.length-1);
		sRut=sRut.substring(0,sRut.length-1);
		while(sRut.length>3){
			rutFormateado="."+sRut.substr(sRut.length-3)+rutFormateado;
			sRut=sRut.substring(0,sRut.length-3);
		}
		rutFormateado=sRut+rutFormateado;
		rutFormateado+="-"+dv;
	}else{
		rutFormateado=sRut;
	}
	id.value=rutFormateado;
}
function  quitarFormatoRut(rut){
	var strRut=new String(rut);
	while(strRut.indexOf(".") !=-1){
		strRut=strRut.replace(".","");
	}
	strRut=strRut.replace("-","");
	return strRut;
}


