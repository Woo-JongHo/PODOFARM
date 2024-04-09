chrome.storage.local.get('syncStorage', (data) => {
  keys = ['id', 'studyCode', 'problemId'];

  if(!data  || !data.syncStorage){
    keys.forEach((key) => {
        chrome.storage.sync.get(key, (data) => {
          chrome.storage.local.set({ [key]: data[key] });
        });
      });
      chrome.storage.local.set({ isSync: true }, (data) => {
        // if (debug)
        console.log('BaekjoonHub Synced to local values');
      });
    } else {
      // console.log('Upload Completed. Local Storage status:', data);
      console.log('Podofarm Storage ready to upload');
    }
  });


async function getObjectFromLocalStorage(key) {
    return new Promise((resolve, reject) => {
      try {
        chrome.storage.local.get(key, function(value) {
          resolve(value[key]);
        
        // 현재,  enable은 on/off 스위치에 연결되어있는걸로 보임, enable은 필요없고, get 방식으로 필요
          console.log("getObjectFromStorage 시도 중 ");
        console.log(value[key]);


        });
      } catch (ex) {
        reject(ex);
      }
    });
  }



  async function getId() {
    return await getObjectFromLocalStorage('id');
  }
  
  async function getStudyCode() {
    return await getObjectFromLocalStorage('StudyCode');
  }
  
  async function getProblemId() {
    return await getObjectFromLocalStorage('problemId');
  }

