const min=1;
const max=50;
const answer =Math.floor(Math.random() * (max-min) + 1) + min;

let attempts=0;
let running=true;
let guess;

while(running){
    guess = window.prompt(`enter a number between ${min} - ${max} `);
    guess=Number(guess);

    if(isNaN(guess)){
        window.alert(`please enter a valid number`);
    }
    else if(guess< min || guess > max){
        window.alert(`please enter value in range`);
    }
    else{
        attempts++;
        if(guess<answer){
            
            window.alert(`TOO LOW! TRY AGAIN`);
        }
        else if(guess>answer){
            window.alert(`TOO LOW! TRY AGAIN!`);

        }else{
            window.alert(`correct guess answer is ${answer} you took ${attempts} to guess`);
            running=false;
        }
    }

}