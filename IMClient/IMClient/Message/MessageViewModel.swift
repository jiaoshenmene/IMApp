//
//  MessageViewModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/18.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation

struct MessageViewModel: ListViewModel {
  
    var models: [MessageModel] {
        return [MessageModel()]
    }
    
    var reloading: Bool = false
    
    var hasMorePage: Bool = false
    
    func numberOfSections() -> Int {
        return 1
    }
    
    func numberOfRows(_ section: Int) -> Int {
        return 10
    }
    
    func cellType(_ indexPath: IndexPath) -> ListViewCell.Type {
        return MessageCell.self
    }
    
    func cellModel(_ indexPath: IndexPath) -> CellModel {
        let model = self.model(at: indexPath)
        return MessageCellModel(model: model)
    }
    
    private func model(at indexPath: IndexPath) -> MessageModel {
        let models = self.models
        // Crash: http://crashes.to/s/716b579cb48
//        guard indexPath.item < models.count else { return models.first ?? models.last ?? Theme.placeholder }
        return models[0]
    }
}


class MessageModel: BaseModel {
    var name = "小明"
    var portrait = ""
    var time = ""
    var msg = "天好蓝"
    
    
    
}
struct MessageCellModel: CellModel {
    private let message: MessageModel
    var name = ""
    var portrait = ""
    var time = ""
    var msg = ""
    
    init(model: BaseModel) {
        message = model as! MessageModel
        name = message.name
        portrait = message.portrait
        time = message.time
        msg = message.msg
    }
    
    
}
