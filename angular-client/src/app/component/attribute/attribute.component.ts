import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';

import { AttributeService } from 'src/app/service/attribute.service';
import { Attribute } from 'src/app/class/attribute'

@Component({
  selector: 'app-attribute',
  templateUrl: './attribute.component.html',
  styleUrls: ['./attribute.component.css']
})
export class AttributeComponent implements OnInit {

  attributes: Attribute[] = [];

  attrForm: FormGroup = new FormGroup({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern('[A-Za-z]+[_-][A-Za-z]+')
      ]),
      value: new FormControl('', [
        Validators.required
      ]),
      description: new FormControl('', [
        Validators.required
      ])
 })

  isUserNameAlreadyExists: boolean = false;

  isUpdatedForm: boolean = false;

  constructor(private _attributeService: AttributeService) { }

  ngOnInit(): void {
    this.getAllAttributes()
  }

  getAllAttributes() {
    this._attributeService.getAllAttributes().subscribe(
      data => {
        this.attributes = data;
      }
    )
  }

  save() {

    var attribute = new Attribute(
      this.attrForm.controls['name'].value,
      this.attrForm.controls['value'].value,
      this.attrForm.controls['description'].value,
    );


    this._attributeService.saveAttribute(attribute).subscribe(
      data => {
        console.log(data)
        this.getAllAttributes()
      }
    )

  }

  update() {
    var attribute = new Attribute(
      this.attrForm.controls['name'].value,
      this.attrForm.controls['value'].value,
      this.attrForm.controls['description'].value,
    );


    this._attributeService.updateAttribute(attribute).subscribe(
      data => {
        console.log(data)
        this.getAllAttributes()
      }
    )

  }


  delete(name) {
    this._attributeService.deleteAttribute(name).subscribe(
      data => {
        console.log(data)
        this.getAllAttributes()
      }
    )


  }

  filterNames() {
    var filteredAttrs = this.attributes.filter(
      attr => {
        if(attr.name === this.attrForm.controls['name'].value) {
          return attr;
        }
    })

    if (filteredAttrs.length === 0) {
      this.isUserNameAlreadyExists = false
    } else {
      this.isUserNameAlreadyExists = true
    }
  }

  switchToUpdateAttr(attr) {
    this.isUpdatedForm = true;
    this.attrForm.controls['name'].setValue(attr.name)
    this.attrForm.controls['value'].setValue(attr.value)
    this.attrForm.controls['description'].setValue(attr.description)
  }

  switchToCreateAttr() {
    this.isUpdatedForm = false;
    this.attrForm.controls['name'].setValue('')
    this.attrForm.controls['value'].setValue('')
    this.attrForm.controls['description'].setValue('')
  }


}
