07/08/2015 - Ultimos cambios:

	. Completé el tema de la carga de los elementos del modelo. Ahora ya carga todos los personajes 
	  en una lista, cada uno con sus atributos y demás. 

	. Toda esa funcionalidad de carga la metí en una clase aparte: ModelLoader. Lo hice para separar un 
	  poco el código y las responsabilidades, sino es un quilombo :p.Le pasas como parámetro inicial 
	  el archivo de excel con el que se va a manejar. Luego de pasar todo el código a la nueva clase, no chequeé la 
          funcionalidad de nuevo, pero supongo que debe funcionar todo igual.

	. Creé un popup que se abre antes de iniciar la nueva partida. Aunque todavía no tiene ninguna funcionalidad,
	  la idea es que ahí seteemos la configuración inicial (cantidad de rondas, dificultad, etc).

	. Acomodé un poco el código en regiones para que quede más prolijo.
	
07/08/2015: 
	. Hice cambios en algunas imágenes y en el archivo de excel, por el tema de los nombres; ya que tengo que tener 		compatibilidad en los nombres para la carga y eso.
	. Agregué y modifiqué bastante código en las clases del controlador y en la clase ModelLoader. Ahora carga los 			personajes y demás en una lista, y las imágenes en una hash<Nombre del personaje, Imagen del personaje>.
	. EL TEMA AC�?: quedó con un error: fijate que tiene que ver con esa variable booleana "isLoaded" que hace que 		las cosas no se carguen todo el tiempo. No funca bien: al ser un único controlador para todas las vistas, al 		cargar otra vista vuelve a inicializar el controlador, y pone la variable isLoaded en false, por lo que 		vuelve a cargar todo devuelta.
	
	. Si tenés alguna duda sobre el funcionamiento pegame una llamada por whatsapp o por el número si estoy 		desconectado. Traté de hacer el código legible. La inspección del código queda para lo último, si es que la 		hacemos jajaja.



	

	
	

12/08/2015 - Quedó super legible el código, y perfectamente ordenado. 
	.Convertí en Singleton el ModelLoder, por lo tabto solo existe uno y se solucionó el tema del isLoaded. se condulta si la lista ya fué cargada.
12/08/2015
	.Transformé en singleton el ModelLoader, y solo existe uno.
	.Separé los Controllers de la nueva partida y el PopUp.
19/08/2015
	-Reaparto de las cartas
	-Mostrar las cartas
	-Animación de la carta al hacerle click, como si la estivieran lanzando
	-Agregué un load HORRRRRRRIIIIIIBBBBLLLLLEEEEE en Personaje, hay que tratar de solucionarlo.
	-Saqué la lista de Imagenes de ModelLoader (se la asigé a los personajes) y optimicé a carga del Excel( baja el O(n^4)->O(n)).
	-Hay que agregar los atributos a la Tabla que sería la carta
	-Hay que mostrar, de alguna manera, las cartas del oponente.
	-Hay que hacer el PlayerVsCpu cuando se selecciona una carta.
	-Hay que preguntar, en el pop up, si se quiere jugar con personajes o grupos.
	-Hay que correegir, si se puede, load de los personajes.
	-Unificar la manera que se accede a los atributos, ya que esta eparado en estrategias y atributos.
26/08/15
	-Se aplico el StageMaster y el SceneMaster, en el ModelLoader. Se acceden desde la clase 
		ModelLoader.getSCENEMASTER()
		ModelLoader.getSTAGEMASTER()
	y nos desde la instancia, ya que son elementos Staticos.
	-Para actualizar un escenario se debe usar
		ModelLoader.getSCENEMASTER().setRoot(XXXX)
	Siendo XXXX un FXML o un elemento de la escena.

2/09/15 Me Quiero matar! No subí los últimos cambios, y me mande una macana y no puedo volver para atras. No se desde cuando apareció. Asi que hoy vuelvo al 26/08/15 :(

	-Separar los Heroes de las Ligas.
	-Ya esta todo completado lo de nueva partida. Solo falta saber quién gana de los dos personajes, y hacer que las cartas vayan al ganador.
	-Modifiqué el scenario, según lo quería Luis.
	-Hay que hacer que las cartas que estan detras de la primera no se vean.(Creo que es simple, lo hago yo cualquier cosa)
	-Seguramente, hice muchas cosas mas, que no recuerdo.




26/09/2015:
	. EN TOTAL HICE 4 COMMITS. EL JUEGO CAMBIO RADICALMENTE. FUNCIONA. FALTAN HACER COSAS MÁS PEQUEÑAS:
		. FUNCIONALIDAD PARA AGREGAR/ELIMINAR/EDITAR LOS PERSONAJES. LA PANTALLA YA LOS MUESTRA.
		. COMPLETAR LA PANTALLA DE LA NUEVA PARTIDA: CUANDO SE TERMINAN LAS CARTAS, DEBE MOSTRAR AL GANADOR
		  Y HACER ALGO EN CONSECUENCIA (ALGUNA ANIMACION, ETC)
		. EL ASPECTO DEL JUEGO EN LINEAS GENERALES DEJA QUE DESEAR: ESTARIA BUENO AGREGAR ALGUNA FUNCION DE
		  "ARRASTRAR LAS CARTAS A LA MESA", AHORA FUNCIONA TODO CON CLICKS; TAMBIEN, RETOCAR EL 
		  LAYOUT DE LOS ELEMENTOS EN LAS DISTINTAS PANTALLAS.
		. TIENE UNA PEQUEÑA BARRA DE MENÚ ARRIBA A LA IZQUIERDA, EN EL MENU PRINCIPAL. MEJORARLA (ASPECTO).
		. REFINAR EL CODIGO, HAY PARTES QUE SE PUEDEN HACER MEJOR.
		. ELIMINAR CODIGO QUE NO SE ESTÉ USANDO.
		. SI RESULTA NECESARIO (A LO ULTIMO, LUEGO DEL VISTO BUENO DE BERDÚN) AGREGAR LA POSIBILIDAD DE ESCOGER
 		  INDIVIDUOS/GRUPOS.
		. AGREGAR MODOS DE DIFICULTAD PARA LA IA (UNA IDEA: LA DIFICULTAD NO SOLO PODRIA ESTAR DETERMINADA POR
		  COMO SE COMPORTA LA IA, SINO QUE TAMBIEN PODRIAMOS "COMPLICAR" O "FACILITAR" LAS COSAS NOSOTROS, COMO 
		  POR EJEMPLO, MOSTRAR LA CARTA QUE ELIGIO LA MAQUINA EN MODO FÁCIL, TAMBIEN PUEDE SER QUE EL ATRIBUTO 
		  A COMPARAR SE ELIJA AL AZAR, LUEGO DE HABER ELEGIDO LA CARTA).


	. PARECE MUCHO PERO ES POCO, ES POCO EL CODIGO QUE HAY QUE AGREGAR PARA ESAS COSAS. LO PEOR YA ESTÁ HECHO JAJA.
		




29-10: Ayer me cruce con Luis en el Isistan y me dijo si tenía el proyecto para verlo. Corrigió un par de cosas:
	-[Done]Borrar la dificultad.
	-Agreguemos en el PopUp que podamos elegir la estrategia:
		♦Manual:
			Como la tenemos ahora
		♦Automatica:
			Donde podamos elegir random o diversas opciones como "El de mayor atributo", el de menor.. etc.
			A su vez acá, se debe pregutar si quiere que las cartas salgan automaticamente del mazo,
			o que nosotros se las saquemos(esto es como esta ahora, que al hacer click sobre el mazo,
			sale la carta).
	 Para esto me dibujo un composite (goo.gl/photos/oJw1AsCDXeJhMRRM8) de como legustaría que sea.
	-[DONE]Las cartas jugadas, se agregan al mazo de quién ganó. En orden, nada de random.
	-[DONE]Puede elegir cuantas rondas quiera, no depende del número de cartas que haya(Viene ligado de lo anterior).
	-Antes del poppup, debe haber otro anterior, que nos de a elegir diferentes mazos.
	-Se deben poder agregar mas personajes. O sea, el mazo que está, queda pero debemos dar las opciones para crear nuevos mazos, en el cual no existen grupos, solo individuos(Para simplificar), pero que si otra persona lo quiere agregar lo de los grupos, este todo preparado para eso.
	Al crear uno, se debe pedirle que atributos van a tener los personajes que se creen en ese mazo, y para todos
	van a ser los mismos. O sea, se arma un template y las cartas se crean sobre esa. Atributos fijos por mazos.
	-[DONE]Por último, quiere que hagamos la clase jugador, donde este todo unido las caractteristicas de este, ya sean:
		♦Gano(Carta1, Carta2,AtribGanador)
			Acá haga lo correspondiente a ganar
		♦Perdió(Carta1, Carta2,AtribPerdedor)
			Lo correspondiente a ganar.
		♦NextCard()
			Se le pide la próxima carta a jugar.
		♦Etc.
	Pero que también tenga las cartas que se les repartieron a ese jugador, y se las vayamos pidiendo a él. A su
	vez, [UNDONE]va a tener los criterios por los que juega, si es que no eligió "Manual". Esto es para el Automatico, para el composite, donde se le va a pedir el criterio que tiene como gandor.

¿Fin? =/
