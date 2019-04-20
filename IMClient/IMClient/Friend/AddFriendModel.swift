//
//  AddFriendModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/20.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation


struct AddFriendViewModel: ListViewModel {
    
    var models: [AddFriendModel] {
        return [AddFriendModel()]
    }
    
    var reloading: Bool = false
    
    var hasMorePage: Bool = false
    
    func numberOfSections() -> Int {
        return 1
    }
    
    func numberOfRows(_ section: Int) -> Int {
        return 1
    }
    
    func cellType(_ indexPath: IndexPath) -> ListViewCell.Type {
        return AddFriendViewCell.self
    }
    
    func cellModel(_ indexPath: IndexPath) -> CellModel {
        let model = self.model(at: indexPath)
        return AddFriendCellModel(model: model)
    }
    
    private func model(at indexPath: IndexPath) -> AddFriendModel {
        let models = self.models
        // Crash: http://crashes.to/s/716b579cb48
        //        guard indexPath.item < models.count else { return models.first ?? models.last ?? Theme.placeholder }
        return models[0]
    }
}

class AddFriendModel: BaseModel {
    var portrait = "text"
    var des = "xiaoming"
    
}
struct AddFriendCellModel: CellModel {
    private let contact: AddFriendModel
    
    var portrait = ""
    var des = ""
    
    init(model: BaseModel) {
        contact = model as! AddFriendModel
        portrait = contact.portrait
        des = contact.des
    }
}
