# 🤖 Mower Kata - Les Tondeuses Autonomes

> _"Quand la technologie rencontre la pelouse, même les tondeuses deviennent intelligentes !"_ 🌱

## 🎯 Description

Ce projet implémente le célèbre **Kata de la Tondeuse Automatique** - un exercice de programmation qui simule des tondeuses autonomes naviguant sur une pelouse rectangulaire.

### 🚀 Fonctionnalités

- **Tondeuses intelligentes** : Elles savent où elles vont (enfin, presque !)
- **Navigation autonome** : Rotation à gauche (G), à droite (D), avancer (A)
- **Détection de limites** : Les tondeuses ne sortent jamais de la pelouse
- **Parsing de fichiers** : Lecture des instructions depuis un fichier texte
- **Tests complets** : 34 tests qui passent tous ! 🎉

## 🛠️ Technologies

- **Java 21** - Pour la logique métier
- **Spring Boot** - Pour le framework (même si on n'utilise que la base)
- **Maven** - Pour la gestion des dépendances
- **JUnit 5** - Pour les tests
- **AssertJ** - Pour des assertions plus expressives

## 📁 Structure du projet

```
src/
├── main/java/com/mowitow/mower/
│   ├── MowerKataApplication.java    # Point d'entrée
│   ├── MowerSimulator.java          # Orchestrateur principal
│   ├── Mower.java                   # La tondeuse elle-même
│   ├── Lawn.java                    # La pelouse
│   ├── Position.java                # Position (x, y)
│   ├── Direction.java               # N, S, E, W
│   ├── Command.java                 # A, G, D
│   ├── MowerFileParser.java         # Parseur de fichiers
│   └── MowerConfiguration.java      # Configuration
└── test/java/com/mowitow/mower/
    ├── IntegrationTest.java         # Tests d'intégration
    ├── MowerTest.java               # Tests de la tondeuse
    ├── DirectionTest.java           # Tests des directions
    ├── PositionTest.java            # Tests des positions
    └── ... (autres tests)
```

## 🚀 Comment utiliser

### Prérequis

- Java 21+
- Maven 3.6+

### Compilation et tests

```bash
mvn clean test
```

### Exécution

```bash
mvn exec:java -D"exec.mainClass=com.mowitow.mower.MowerKataApplication" -D"exec.args=src/main/resources/input.txt"
```

## 📝 Format du fichier d'entrée

```
5 5                    # Dimensions de la pelouse (largeur hauteur)
1 2 N                  # Position initiale et direction de la 1ère tondeuse
GAGAGAGAA             # Instructions pour la 1ère tondeuse
3 3 E                  # Position initiale et direction de la 2ème tondeuse
AADAADADDA            # Instructions pour la 2ème tondeuse
```

### Instructions disponibles

- **A** : Avancer d'une case
- **G** : Tourner à gauche (90°)
- **D** : Tourner à droite (90°)

## 📊 Exemple de sortie

```
1 3 N
5 1 E
```

## 🧪 Tests

Le projet suit une approche **TDD (Test-Driven Development)** avec :

- **Tests unitaires** pour chaque classe
- **Tests d'intégration** pour valider le comportement global
- **Tests de cas limites** (positions invalides, fichiers vides, etc.)

```bash
# Lancer tous les tests
mvn test

# Lancer un test spécifique
mvn test -Dtest=IntegrationTest#should_execute_complete_kata_test_case
```

## 🎭 Le Kata en action

```
Avant : 🌱🌱🌱🌱🌱
         🌱🤖🌱🌱🌱  (tondeuse au centre)
         🌱🌱🌱🌱🌱

Après :  🌱🌱🌱🌱🌱
         🌱🌱🌱🌱🌱
         🌱🌱🤖🌱🌱  (tondeuse déplacée)
```

## 🤝 Contribution

Ce projet a été réalisé dans le cadre d'un exercice technique. Les contributions sont les bienvenues, mais attention aux bugs - les tondeuses pourraient se perdre ! 😄

## 📄 Licence

Ce projet est sous licence MIT. Libre comme une tondeuse dans un champ ! 🌾

---

_Développé avec ❤️ et beaucoup de café ☕_

_"La meilleure tondeuse est celle qui ne vous demande pas de la pousser !"_ 🤖
