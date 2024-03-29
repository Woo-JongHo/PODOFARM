// Set to true to enable console log
const debug = false;

/* 
  문제 제출 맞음 여부를 확인하는 함수
  2초마다 문제를 파싱하여 확인
*/
let loader;

const currentUrl = window.location.href;

// 프로그래머스 연습 문제 주소임을 확인하고, 맞다면 로더를 실행
if (currentUrl.includes('/learn/courses/30') && currentUrl.includes('lessons')) startLoader();

function startLoader() {
  loader = setInterval(async () => {
    console.log("포도팜 익스텐션 실행중입니다");


    //const enable = await checkEnable();

    if(!enable) stopLoader();
    else if (getSolvedResult().includes('정답')) {
      console.log('포도팜 정답이 나왔습니다. 업로드를 시작합니다.');
      try {
        const PodoData = await parseData();
        await beginUpload(PodoData);
      
        //사용자정보만 가져오는지 확인
      
      
      
      
      
      
      } catch (error) {
        console.log(error);
      }
    }
  }, 2000);
}

function stopLoader() {
  clearInterval(loader);
}

function getSolvedResult() {
  const result = document.querySelector('div.modal-header > h4');
  if (result) return result.innerText;
  return '';
}

/* 파싱 직후 실행되는 함수 */
async function beginUpload(PodoData) {
  log('PodaData입니다.', PodoData);
  if (isNotEmpty(PodoData)) {
    startUpload();

    //const stats = await getStats();
    //const hook = await getHook();

    const currentVersion = stats.version;
    /* 버전 차이가 발생하거나, 해당 hook에 대한 데이터가 없는 경우 localstorage의 Stats 값을 업데이트하고, version을 최신으로 변경한다 */
    if (isNull(currentVersion) || currentVersion !== getVersion() || isNull(await getStatsSHAfromPath(hook))) {
      await versionUpdate();
    }

    /* 현재 제출하려는 소스코드가 기존 업로드한 내용과 같다면 중지 */
    cachedSHA = await getStatsSHAfromPath(`${hook}/${PodoData.directory}/${PodoData.fileName}`)
    calcSHA = calculateBlobSHA(PodoData.code)
    log('cachedSHA', cachedSHA, 'calcSHA', calcSHA)
    if (cachedSHA == calcSHA) {
      markUploadedCSS(stats.branches, PodoData.directory);
      console.log(`현재 제출번호를 업로드한 기록이 있습니다. problemIdID ${PodoData.problemId}`);
      return;
    }
    /* 신규 제출 번호라면 새롭게 커밋  */
    await uploadOneSolveProblemOnPodo(PodoData, markUploadedCSS);
  }
}

async function versionUpdate() {
  log('start versionUpdate');
  const stats = await updateLocalStorageStats();
  // update version.
  stats.version = getVersion();
  await saveStats(stats);
  log('stats updated.', stats);
}

// /* TODO: 하나의 데이터만 가져오는 구조이므로 page를 계속적으로
//   아래 있는 네이베이션바의 "다음"버튼이 비활성화 될때까지 반복으로 진행한다.
//   진행하며 존재하는 알고리즘 카드인 div.col-item > div.card-algorithm > a 의 href 속성값을 가져와 리스트화하고,
//   이를 차후 fetch GET를 진행하여 작성한 알고리즘을 가져와 github에 업로드를 진행한다.
//   */
// function get_all_problems() {}