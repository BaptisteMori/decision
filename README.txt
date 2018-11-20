|--------------------------------------------------|
|Nom       | Prénom    | Numéro étudiant | filière |
|---------------------------------------------------
|AUBRY     | NICOLAS   | 21603763        | INFO    |
|CHAGNEUX  | DIMITRI   | 21606807        | INFO    |
|BEAUCHAMP | AYMERIC   | 21301016        | INFO    |
|MORI      | BAPTISTE  | 21602052        | INFO    |
|--------------------------------------------------|


Pour lancer la compilation il faut se rendre dans le dossier scripts/
Puis ensuite lancer le script compile-all.sh, qui permettra de compiler
tout les .java dans tout les packages.

Ensuite pour lancer le Main d'un des packages, il suffit de se rendre dans le
dossier scripts/ puis de lancer l'un des scripts commençant par :

        - launch-DIAGNOSIS.sh : pour la partie Diagnosis.
        - launch-EXTRACTION.sh : pour la partie Extraction.
        - launch-PLANNING.sh : pour la partie Planning.
        - launch-PPC.sh : pour la partie PPC.
        - launch-REPRESENTATIONS.sh : pour la partie Representaions


Ensuite pour pouvoir générer la Javadoc de notre projet. Il suffit de se rendre
à nouveau dans le dossier scripts/ et de lancer le script suivant:

    - generateJavadoc.sh

Un nouveau dossier sera apparut parmis les packages avec pour nom : Javadoc


Pour pouvoir supprimer tous les .class il suffit de lancer le script :

  - clean-all.sh

Pour pouvoir supprimer le dossier contenant la Javadoc, il suffit de lancer le
script suivant :

  - clean-javadoc.sh

  
