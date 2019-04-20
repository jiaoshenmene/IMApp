//
//  ContactViewModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/19.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation

struct ContactViewModel: ListViewModel {
    
    var models: [ContactModel] {
        return [ContactModel()]
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
        return ContactViewCell.self
    }
    
    func cellModel(_ indexPath: IndexPath) -> CellModel {
        let model = self.model(at: indexPath)
        return ContactCellModel(model: model)
    }
    
    private func model(at indexPath: IndexPath) -> ContactModel {
        let models = self.models
        // Crash: http://crashes.to/s/716b579cb48
        //        guard indexPath.item < models.count else { return models.first ?? models.last ?? Theme.placeholder }
        return models[0]
    }
}


class ContactModel: BaseModel {
    var portrait = "text"
    var name = "xiaoming"
    
}
struct ContactCellModel: CellModel {
    private let contact: ContactModel
    
    var portrait = ""
    var name = ""
    
    init(model: BaseModel) {
        contact = model as! ContactModel
        portrait = contact.portrait
        name = contact.name
    }
}
