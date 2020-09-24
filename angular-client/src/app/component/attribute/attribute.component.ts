import { Component, OnInit } from '@angular/core';
import { AttributeService } from 'src/app/service/attribute.service';
import { Attribute } from 'src/app/class/attribute'

@Component({
  selector: 'app-attribute',
  templateUrl: './attribute.component.html',
  styleUrls: ['./attribute.component.css']
})
export class AttributeComponent implements OnInit {

  attributes: Attribute[] = [];

  constructor(private _attributeService: AttributeService) { }

  ngOnInit(): void {
    this.getAllAttributes()
  }

  getAllAttributes() {
    this._attributeService.getAllAttributes().subscribe(
      data => {
        this.attributes = data;
        console.log(this.attributes)
      }
    )
  }


}
