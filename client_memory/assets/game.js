const selectors = {
    boardContainer: document.querySelector('.board-container'),
    board: document.querySelector('.board'),
    moves: document.querySelector('.moves'),
    timer: document.querySelector('.timer'),
    start: document.querySelector('button'),
    win: document.querySelector('.win')
}

const state = {
    gameStarted: false,
    flippedCards: 0,
    totalFlips: 0,
    totalTime: 0,
    loop: null
}

const shuffle = array => {
    const clonedArray = [...array]

    for (let index = clonedArray.length - 1; index > 0; index--) {
        const randomIndex = Math.floor(Math.random() * (index + 1))
        const original = clonedArray[index]

        clonedArray[index] = clonedArray[randomIndex]
        clonedArray[randomIndex] = original
    }

    return clonedArray
}

const pickRandom = (array, items) => {
    const clonedArray = [...array]
    const randomPicks = []

    for (let index = 0; index < items; index++) {
        const randomIndex = Math.floor(Math.random() * clonedArray.length)
        
        randomPicks.push(clonedArray[randomIndex])
        clonedArray.splice(randomIndex, 1)
    }

    return randomPicks
}

const generateGame = () => {
    const dimensions = selectors.board.getAttribute('data-dimension')

    if (dimensions % 2 !== 0) {
        throw new Error("The dimension of the board must be an even number.")
    }

    const emojis = ['🥔', '🍒', '🥑', '🌽', '🥕', '🍇', '🍉', '🍌', '🥭', '🍍']
    const picks = pickRandom(emojis, (dimensions * dimensions) / 2) 
    const items = shuffle([...picks, ...picks])
    const cards = `
        <div class="board" style="grid-template-columns: repeat(${dimensions}, auto)">
            ${items.map(item => `
                <div class="card">
                    <div class="card-front"></div>
                    <div class="card-back">${item}</div>
                </div>
            `).join('')}
       </div>
    `
    
    const parser = new DOMParser().parseFromString(cards, 'text/html')

    selectors.board.replaceWith(parser.querySelector('.board'))
}

const startGame = () => {
    state.gameStarted = true
    selectors.start.classList.add('disabled')

    state.loop = setInterval(() => {
        state.totalTime++

        selectors.moves.innerText = `${state.totalFlips} moves`
        selectors.timer.innerText = `time: ${state.totalTime} sec`
    }, 1000)
}

const flipBackCards = () => {
    document.querySelectorAll('.card:not(.matched)').forEach(card => {
        card.classList.remove('flipped')
    })

    state.flippedCards = 0
}

const flipCard = card => {
    state.flippedCards++
    state.totalFlips++

    if (!state.gameStarted) {
        startGame()
    }

    if (state.flippedCards <= 2) {
        card.classList.add('flipped')
    }

    if (state.flippedCards === 2) {
        const flippedCards = document.querySelectorAll('.flipped:not(.matched)')

        if (flippedCards[0].innerText === flippedCards[1].innerText) {
            flippedCards[0].classList.add('matched')
            flippedCards[1].classList.add('matched')
        }

        setTimeout(() => {
            flipBackCards()
        }, 1000)
    }

    // If there are no more cards that we can flip, we won the game
    if (!document.querySelectorAll('.card:not(.flipped)').length) {
        setTimeout(() => {
            selectors.boardContainer.classList.add('flipped')
            selectors.win.innerHTML = `
                <span class="win-text">
                    You won!<br />
                    with <span class="highlight">${state.totalFlips}</span> moves<br />
                    under <span class="highlight">${state.totalTime}</span> seconds
                </span>
            `
            score = calculateScore(state.totalTime,state.totalFlips)
            sendScore("addEntree",score)

            clearInterval(state.loop)
        }, 1000)
    }
}

const attachEventListeners = () => {
    document.addEventListener('click', event => {
        const eventTarget = event.target
        const eventParent = eventTarget.parentElement

        if (eventTarget.className.includes('card') && !eventParent.className.includes('flipped')) {
            flipCard(eventParent)
        } else if (eventTarget.nodeName === 'BUTTON' && !eventTarget.className.includes('disabled')) {
            startGame()
        }
    })
}

generateGame()
attachEventListeners()

function chargerClassementSuccess(data, text, jqXHR) {
    const classement = document.getElementById('classement');
    classement.innerHTML = '';
    console.log(data);

    // Create the table element and its header row
    const table = document.createElement('table');
    const headerRow = document.createElement('tr');
    const headers = ['Score', 'Name'];
    headers.forEach(function(headerText) {
        const headerCell = document.createElement('th');
        headerCell.textContent = headerText;
        headerRow.appendChild(headerCell);
    });
    table.appendChild(headerRow);

    // Sort the data by score in descending order
    data.sort(function(a, b) {
        const scoreA = parseInt(a.split(', ')[1]);
        const scoreB = parseInt(b.split(', ')[1]);
        return scoreB - scoreA;
    });

    // Populate the table with the data, only showing the first 10 rows
    const rowLimit = 10;
    let rowCount = 0;
    data.forEach(function(rowText) {
        if (rowCount >= rowLimit) {
            return;
        }
        const rowValues = rowText.split(', ');
        const score = rowValues[1];
        const name = rowValues[2];
        const row = document.createElement('tr');
        const scoreCell = document.createElement('td');
        scoreCell.textContent = score;
        const nameCell = document.createElement('td');
        nameCell.textContent = name;
        row.appendChild(scoreCell);
        row.appendChild(nameCell);
        table.appendChild(row);
        rowCount++;
    });

    // Add the table to the DOM
    classement.appendChild(table);
}






function chargerClassementError(request, status, error) {
    alert("erreur : " + error + ", request: " + request + ", status: " + status);
}



function calculateScore(time, moves) {
    // Assuming time is in seconds and moves is a positive integer
    const timeScore = Math.max(10000 - (time * 100), 0); // Maximum score of 10000, deduct 100 points for each second taken
    const movesScore = Math.max(10000 - (moves * 100), 0); // Maximum score of 10000, deduct 100 points for each move made
    const totalScore = (timeScore + movesScore) / 2; // Average the two scores to get the final score
    return Math.round(totalScore); // Round the score to the nearest integer
}

function sendScoreSuccess(response) {
    console.log("Score sent successfully", response);
}

function sendScoreError(error) {
    console.error("Error sending score", error);
}


$(document).ready(function () {

    $.getScript("assets/servicesHttp.js", function () {
        console.log("servicesHttp.js chargé !");
        chargerClassement(chargerClassementSuccess, chargerClassementError);
        //sendScore("addEntree","60", sendScoreSuccess, sendScoreError);
    });

});