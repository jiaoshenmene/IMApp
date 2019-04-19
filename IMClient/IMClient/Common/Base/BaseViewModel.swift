//
//  BaseViewModel.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/18.
//  Copyright © 2019 杜甲. All rights reserved.
//

import Foundation

protocol ViewModel {
    
}

protocol ListViewModel: ViewModel {
    var reloading: Bool { get set }
    var hasMorePage: Bool { get }
    func numberOfSections() -> Int
    func numberOfRows(_ section: Int) -> Int
    func cellType(_ indexPath: IndexPath) -> ListViewCell.Type
    func cellModel(_ indexPath: IndexPath) -> CellModel
}

protocol CellModel {
    init(model: BaseModel)
}
