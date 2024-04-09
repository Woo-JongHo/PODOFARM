chrome.storage.local.get('syncStorage', (data) => {
  keys = ['id', 'StudyCode', 'problemId'];

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
        });
      } catch (ex) {
        reject(ex);
      }
    });
  }