//
//  BaseTableViewCell.swift
//  IMClient
//
//  Created by 杜甲 on 2019/4/18.
//  Copyright © 2019 杜甲. All rights reserved.
//

import UIKit

protocol ListViewCell {
    var viewController: UIViewController? { get set }
    var cellModel: CellModel! { get set }
    static var reuseIdentifier: String { get }
    func updateUI()
    func layoutUI()
    static func height(_ data: CellModel?) -> CGFloat
    static func size(_ data: CellModel?) -> CGSize
    
}

class BaseTableViewCell: UITableViewCell, ListViewCell {
    
    @IBOutlet weak var viewController: UIViewController?
    
    var cellModel: CellModel! {
        didSet{
            self.configUI()
            self.updateUI()
        }
    }
    
    
    class var reuseIdentifier: String{
        return NSStringFromClass(self).components(separatedBy: ".").last ?? NSStringFromClass(self);
        
    }
    
    class func register(_ tableView: UITableView) {
        let nib = UINib(nibName: NSStringFromClass(self), bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: self.reuseIdentifier)
    }
    
    func configUI() {}
    
    func updateUI() {
    }
    
    func layoutUI() {
    }
    
    class func height(_ data: CellModel?) -> CGFloat {
        return 0
    }
    
    class func size(_ data: CellModel?) -> CGSize {
        return CGSize.zero
    }
    
    
}
