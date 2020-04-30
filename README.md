# Zog-zog Akita !

Ce petit jar exécutable démarre un service REST sur le port 8080

Le service expose une collection d'entités *Toto*. Chaque *Toto* dispose d'une liste de *Zogzog*s. Chaque *Zogzog* a une valeur textuelle.

Il y a 3 entités *Toto* identifiable par leur id : 1, 2 et 3.

Ce service expose 3 endpoints (toujours méthode GET pour simplifier l'usage avec un navigateur) :
* `GET toto` : récupère la liste des entités Toto
* `GET toto/add/{id}/{value}` : ajoute à l'entité Toto désignée par son {id} un zogzog de valeur {value}. Retourne l'entité avec son nouveau zogzog.
* `GET toto/cancel/{id}` : annule une entité Toto (c'est à dire supprime tous ses zogzog). Retourne l'entité telle qu'elle était avant annulation. Affiche également une trace dans la sortie standard du serveur.
