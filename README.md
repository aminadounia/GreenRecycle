- About the project :
	Our solution to tackle the issue of waste and environmental pollution is GreenCycle - a mobile application that serves as a "be-to-be" system.
	The app connects people and organizations that possess recyclable materials and wish to derive value from them, rather than discarding them and 
	further contributing to pollution. GreenCycle also links these individuals with organizations that specialize in collecting and recycling these products. 
	The app's user-friendly interface allows users to create announcements showcasing what they wish to sell or give away, while recyclers can easily browse
	these offers in the app.
	GreenCycle incorporates a unique feature that differentiates recyclable products from other waste items, providing recyclers with the assurance that 
	they will only access materials suitable for the recycling process. Furthermore, the app categorizes products systematically, including plastic, wood, organic waste,
	and others. This ensures that users can quickly and efficiently navigate through the app to find the products they require. With GreenCycle, we aim to encourage 
	individuals and organizations to make sustainable choices and contribute to a healthier, cleaner planet.

- L'application moblie Greencycle est developee en :
 	- Kotlin pour Frontend 
	- Nodejs pour Backend avec l'architecture REST
	- Mysql est utilise pour la bdd

- Pour executer l'application :
	-ouvrir Xampp et lancer Apache et Mysql
	-Importer la bdd greencycle dans phpmyadmin qui se trouve dans le dossier bddgreencycle
	-Ouvrir ngrok
	-ecrire la commande ngrok http 8082 et copier l'url dans le const.kt ( constante ) de l'application	
	-lancer le cmd dans le dossier bddgreencycle
	-Ecire la commande node rest

- Il existe 2 types d'utilisateurs pour se connecter :
le profil provider (volanteer)  : mail: iris@gmail.com  password :123
le profil recycler : mail: ji_soumes@esi.dz   password :123

- Le code est organise en 4 activites : 
	- L'activite StartActivity est pour Splach screen 
	- L'activite MainActivity0 englobe les interfaces de sign in et sign up
	- L'activite MainActivity englobe les interfaces destines a l'utilisateur type 1
	- L'activite MainActivity2 englobe les interfaces destines a l'utilisateur type 2

- L'organisation de MainActivity :
	- Fragment 1 : 
		- Afficher la liste des annoncements 
		- Faire un filtre sur les annoncements par categories en cliquant sur les boutons (plastic,organic..)
		- Cliquer sur un element de la liste pour aller au fragment Detail et voir les details de l'annoncement
	- Fragment Detail :
		- Afficher les details d'un annoncement
		- Cliquer sur le boutton order pour commander un annoncement, une fois traitement complet on vous redirige au fragment Order pour voir 
                  le commande ajoutee a la liste de vos commandes 
	- Fragment Order :
		- Afficher la liste de vos commandes ( en attente ou acceptees)
		- Cliquer sur cancel pour annuler une commande
	- Fragment Profil :
		- Afficher les informations sur l'utilisateur 
		- En cliquant sur le boutton activity on vous dirige vers le fragment Activity pour voir les informations sur l'activite de l'utilisateur 
	- Fragment Activity:
		- Afficher les informations sur l'activite de l'utilisateur 

- L'organisation de MainActivity2 :
	- Fragment 4 :
		- Afficher la liste des annoncements qu'a faits l'utilisateur
		- Faire un filtre sur les annoncements par categories en cliquant sur les boutons (plastic,organic..)
		- Cliquer sur le bouton + pour ajouter un annoncement
	- Fragment 5 :
		- Remplir les informations sur l'annoncement
		- Cliquer sur le bouton scanner pour se rediriger ves le fragment scanner
	- Fragment Scanner :
		- ajouter une photo du produit 
		- Cliquer sur le bouton pour terminer l'ajout de l'annoncement
	- Fragment 6 :
		- Afficher la liste des commandes qu'a recu l'utilisateur sur les annoncements qu'a publies ( valides et recues )
	- Fragment Profil :
		- Afficher les informations sur l'utilisateur 
		- En cliquant sur le boutton activity on vous dirige vers le fragment Activity pour voir les informations sur l'activite de l'utilisateur 
	- Fragment Activity:
		- Afficher les informations sur l'activite de l'utilisateur 

- Vous trouverez dans l'interface Endpoint tous les services GET et POST utilises
	
