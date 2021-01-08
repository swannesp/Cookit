# Cookit
Projet RMI - Middleware M2

Le principe de ce projet est le suivant: mettre en oeuvre la technologie RMI de Java et des moniteurs dans un jeu de cuisine. Le but est simple, deux joueurs s'affrontent, chacun ayant une recette de cuisine a accomplir et des ustensiles a sa disposition, ustensiles étant en nombre limités et partagés entre les deux joueurs, et donc à acceder en concurrence.
Les RMI sont utilisées pour connecter les objet joueur à l'objet servveur, puis les joueurs sont distribués à des parties et communiquent dès lors avec leur objet Game.
Nous avons utilisé les moniteurs tout d'abord afin de gerer le nombre de joueurs pouvant rejoindre une partie, puis grace à l'objet Usable, objet representant un set d'ustensile de meme nature (couteaux, four), et contenant un nombre correspondant le nombre disponible dans la partie et permettant de faire attendre les joueurs si aucun n'est disponible.

La classe ServerDriver doit etre lancée en activant préalablement le rmiregistry avant de pouvoir y connecter des clients.


Nous n'avons malheureusement pas réussi à terminer le projet dans les temps. L'interface permet de crééer et de rejoindre une partie grace à un code, néanmoins le jeu en lui même n'est pas fonctionnel. Les moniteurs Usable sont quand même implémentés même s'il ne sont pas utilisé pour l'instant.

Projet RMI 2020-2021, Alissa Cosson & Swann Espagne.
