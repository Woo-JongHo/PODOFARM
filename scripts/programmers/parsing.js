async function parseData() {
  const link = document.querySelector('head > meta[name$=url]').content.replace(/\?.*/g, '').trim();
  const problemId = document.querySelector('div.main > div.lesson-content').getAttribute('data-lesson-id');
  const level = levels[problemId] || 'unrated';
  const division = [...document.querySelector('ol.breadcrumb').childNodes]
      .filter((x) => x.className !== 'active')
      .map((x) => x.innerText)
      .map((x) => convertSingleCharToDoubleChar(x))
      .reduce((a, b) => `${a}/${b}`);

  const title = document.querySelector('.nav-item.algorithm-nav-link.algorithm-title.active .challenge-title').textContent.trim();
  const problem_description = document.querySelector('div.guide-section-description > div.markdown').innerHTML;
  const language_extension = document.querySelector('div.editor > ul > li.nav-item > a').innerText.split('.')[1];
  const code = document.querySelector('textarea#code').value;
  const result_message =
      [...document.querySelectorAll('#output .console-message')]
          .map((node) => node.textContent)
          .filter((text) => text.includes(':'))
          .reduce((cur, next) => (cur ? `${cur}<br/>${next}` : next), '') || 'Empty';
  const [runtime, memory] = [...document.querySelectorAll('td.result.passed')]
      .map((x) => x.innerText)
      .map((x) => x.replace(/[^., 0-9a-zA-Z]/g, '').trim())
      .map((x) => x.split(', '))
      .reduce((x, y) => (Number(x[0]) > Number(y[0]) ? x : y), ['0.00ms', '0.0MB'])
      .map((x) => x.replace(/(?<=[0-9])(?=[A-Za-z])/, ' '));

  console.log("link Data + " + link);
  console.log("ProblemId Data + " + problemId);
  console.log("level + " + level);
  console.log("title + " + title);
  console.log("problem_description + " + problem_description);
  console.log("division + " + division);
  console.log("language_extension + " + language_extension);
  console.log("code + " + code);
  console.log("result_message + " + result_message);
  console.log("runtime + " + runtime);
  console.log("memory + " + memory);

  return makeData({ link, problemId, level, title, problem_description, division, language_extension, code, result_message, runtime, memory });
}

async function makeData(origin) {
  const { link, problem_description, problemId, level, result_message, division, language_extension, title, runtime, memory, code } = origin;
  const directory = `프로그래머스/${level}/${problemId}. ${convertSingleCharToDoubleChar(title)}`;
  const message = `[${level.replace('lv', 'level ')}] Title: ${title}, Time: ${runtime}, Memory: ${memory} -BaekjoonHub`;
  const fileName = `${convertSingleCharToDoubleChar(title)}.${language_extension}`;
  // prettier-ignore
  const readme =
      `# [${level.replace('lv', 'level ')}] ${title} - ${problemId} \n\n`
      + `[문제 링크](${link}) \n\n`
      + `### 성능 요약\n\n`
      + `메모리: ${memory}, `
      + `시간: ${runtime}\n\n`
      + `### 구분\n\n`
      + `${division.replace('/', ' > ')}\n\n`
      + `### 채점결과\n\n`
      + `${result_message}\n\n`
      + `### 문제 설명\n\n`
      + `${problem_description}\n\n`
      + `> 출처: 프로그래머스 코딩 테스트 연습, https://programmers.co.kr/learn/challenges`;
  return { problemId, directory, message, fileName, readme, code };
}