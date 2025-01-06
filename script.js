document.addEventListener('DOMContentLoaded', () => {
    // Game screens
    const titleScreen = document.getElementById('title-screen');
    const battleScreen = document.getElementById('battle-screen');
    const gameOverScreen = document.getElementById('game-over-screen');
    const gameOverMessage = document.getElementById('game-over-message');
  
    // Buttons
    const startButton = document.getElementById('start-button');
    const attackButton = document.getElementById('attack-button');
    const restartButton = document.getElementById('restart-button');
  
    // Health displays
    const heroHealthElement = document.getElementById('hero-health');
    const enemyHealthElement = document.getElementById('enemy-health');
  
    // Game variables
    let heroHealth = 100;
    let enemyHealth = 100;
  
    // Start Game
    startButton.addEventListener('click', () => {
      titleScreen.classList.remove('active');
      battleScreen.classList.add('active');
    });
  
    // Attack Action
    attackButton.addEventListener('click', () => {
      const heroDamage = getRandomDamage(15, 25);
      const enemyDamage = getRandomDamage(10, 20);
  
      // Hero attacks enemy
      enemyHealth -= heroDamage;
      if (enemyHealth < 0) enemyHealth = 0;
      enemyHealthElement.textContent = enemyHealth;
  
      // Enemy retaliates
      if (enemyHealth > 0) {
        heroHealth -= enemyDamage;
        if (heroHealth < 0) heroHealth = 0;
        heroHealthElement.textContent = heroHealth;
      }
  
      // Check for victory or defeat
      if (enemyHealth === 0) {
        endGame('Victory! You defeated the darkness!');
      } else if (heroHealth === 0) {
        endGame('Defeat! Darkness consumed the land.');
      }
    });
  
    // Restart Game
    restartButton.addEventListener('click', () => {
      resetGame();
    });
  
    // End Game
    function endGame(message) {
      battleScreen.classList.remove('active');
      gameOverScreen.classList.add('active');
      gameOverMessage.textContent = message;
    }
  
    // Reset Game
    function resetGame() {
      heroHealth = 100;
      enemyHealth = 100;
      heroHealthElement.textContent = heroHealth;
      enemyHealthElement.textContent = enemyHealth;
      gameOverScreen.classList.remove('active');
      titleScreen.classList.add('active');
    }
  
    // Helper: Random Damage
    function getRandomDamage(min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    }
  });