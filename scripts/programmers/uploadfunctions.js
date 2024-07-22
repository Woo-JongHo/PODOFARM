/** 푼 문제들에 대한 단일 업로드는 uploadGit 함수로 합니다.
 * 파라미터는 아래와 같습니다.
 * @param {string} filePath - 업로드할 파일의 경로
 * @param {string} sourceCode - 업로드하는 소스코드 내용
 * @param {string} readme - 업로드하는 README 내용
 * @param {string} filename - 업로드할 파일명
 * @param {string} commitMessage - 커밋 메시지
 * @param {function} cb - 콜백 함수 (ex. 업로드 후 로딩 아이콘 처리 등)
 * @returns {Promise<void>}
 * 
 * 현재 CB가 없어서  주석
 */


async function uploadOneSolveProblemOnPodo(PodoData) {
  const id = await getId();
  const studyCode = await getStudyCode();
  if (isNull(id) || isNull(studyCode)) {
    console.error('id, studtyCode Null', id, studyCode);
    return;
  }

  //cb 삭제 
  return upload(id, studyCode, PodoData.code, PodoData.readme, PodoData.fileName, PodoData.message,PodoData.dateInfo, PodoData.problemId, PodoData.level);
}

/*
 * @param {string} token - github api 토큰
 * @param {string} hook - github api hook
 * @param {string} sourceText - 업로드할 소스코드
 * @param {string} readmeText - 업로드할 readme
 * @param {string} directory - 업로드할 파일의 경로
 * @param {string} filename - 업로드할 파일명
 * @param {string} commitMessage - 커밋 메시지
 * @param {function} cb - 콜백 함수 (ex. 업로드 후 로딩 아이콘 처리 등)
 */

async function upload(id, studyCode, sourceText, readmeText, filename, commitMessage, dateInfo, problemId, level) {
  try {
    console.log("dateInfo" + dateInfo);
    console.log("-----------------------");
    console.log("업로드 function에 도착");
    console.log("id: " + id);
    console.log("studyCode  : " + studyCode);
    console.log("sourceText : " + sourceText);
    console.log("readmeText : " + readmeText);
    console.log("filename :" + filename);
    console.log("commitMessage :" + commitMessage);
    console.log("problemId" + problemId);
    console.log("level" + level);


    /* 업로드 후 서버로 보내기 */
    const response = await fetch('http://localhost:8080/ps', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: id,
        studyCode: studyCode,
        sourceText: sourceText,
        readmeText: readmeText,
        filename: filename,
        commitMessage: commitMessage,
        dateInfo: dateInfo,
        problemId : problemId,
        level : level
      })
    });

    const data = await response.text();

    /* 보내고 스토리지에 문제번호 저장하기 */

    /* 콜백 함수 실행 */
    if (typeof cb === 'function') {
      cb(data); // 콜백 함수에 서버로부터 받은 데이터를 전달
    }
  } catch (error) {
    console.error('Error uploading:', error);
  }
}