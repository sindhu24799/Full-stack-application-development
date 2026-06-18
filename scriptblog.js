const audio = document.getElementById("blogAudio");
const playBtn = document.getElementById("playAudio");
const pauseBtn = document.getElementById("pauseAudio");

playBtn.addEventListener("click", () => {
  audio.play();
});

pauseBtn.addEventListener("click", () => {
  audio.pause();
});
