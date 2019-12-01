import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  operationKeyPrefix = 'operation';

  constructor() { }

  pushLocalStorage(operation, postError) {

    localStorage.setItem(this.operationKeyPrefix + localStorage.length, JSON.stringify(operation));
    this.submitLocalStorage(this.removeLocalStorage, postError);

  }

  submitLocalStorage(postAction, postError) {

    for (let index = 0; index < localStorage.length; index++) {

      const key: string = localStorage.key(index);
      if (key.startsWith(this.operationKeyPrefix)) {
        const jsonMessage: string = localStorage.getItem(key);
        guiDao.writeCashSale(jsonMessage, postAction, index, postError);
      }
    }
  }

  removeLocalStorage(posIndex) {

    localStorage.removeItem('action' + posIndex);
    localStorage.removeItem('sent' + posIndex);

    var storedAction = null;
    for (var index = localStorageQueue.maxSize - 1; index >= 5
      && storedAction == null; index--) {
      storedAction = localStorage.getItem('action' + index);
      if (storedAction == null) {
        localStorage.removeItem('sent' + index);
        localStorageQueue.maxSize = index + 1;
        localStorage.setItem('maxSize', localStorageQueue.maxSize);
      }
    }

  }

  clearLocalStorage() {
    localStorage.clear();
    localStorageQueue.maxSize = 5;
    localStorage.setItem('maxSize', localStorageQueue.maxSize);
  }

  getAll() {

    var actionList = new Array();

    for (var i = 0; i < localStorageQueue.maxSize; i++) {
      var action = localStorage.getItem('action' + i) + " / sent : "
        + localStorage.getItem('sent' + i);
      actionList.push(action);
    }

    return actionList;
  }

  listLocalStorageQueue() {

    var actionList = this.getAll();
    console.log("html.listLocalStorageQueue]action list size "
      + actionList.length);

    for (var i = 0; i < actionList.length; i++) {
      var action = actionList[i];
      console.log("local storage " + i + " : " + action);
    }
  }
}